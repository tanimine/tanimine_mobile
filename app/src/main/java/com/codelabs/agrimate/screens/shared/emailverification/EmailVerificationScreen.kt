package com.codelabs.agrimate.screens.shared.emailverification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.components.AGAuthTopBanner
import com.codelabs.agrimate.ui.components.AGButton
import com.codelabs.agrimate.ui.components.AGInputId
import com.codelabs.agrimate.ui.components.AGInputLayout
import com.codelabs.agrimate.ui.components.AGMessageDialog
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.AgrimateTheme
import com.codelabs.agrimate.ui.theme.Green100
import com.codelabs.agrimate.utils.TimeUtils

@Composable
fun EmailVerificationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    timerViewModel: TimerViewModel = hiltViewModel()
) {
    val timerValue = timerViewModel.timerValue.collectAsStateWithLifecycle().value
    val isCountDown = timerViewModel.play.collectAsStateWithLifecycle().value

    val navigateToForgotPassword = {
        navController.popBackStack()
        navController.navigate(AGRoute.Auth.ForgotPassword.route)
    }

    LaunchedEffect(Unit) {
        timerViewModel.start(900)
    }
    EmailVerificationContent(modifier = modifier, timerValue)

    if (!isCountDown) {
        AGMessageDialog(
            onDismissRequest = navigateToForgotPassword,
            icon = painterResource(id = R.drawable.ic_time_over),
            title = "Waktu Habis",
            description = "Silahkan coba lagi!"
        )
    }
}

@Composable
fun EmailVerificationContent(modifier: Modifier = Modifier, timerValue: Int) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AGAuthTopBanner(title = "Verifikasi Email")
        Spacer(modifier = Modifier.padding(bottom = 28.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(97.dp)
                    .aspectRatio(1f),
                painter = painterResource(id = R.drawable.ag_auth_verification),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Kode verifikasi OTP telah dikirimkan",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = buildAnnotatedString {
                        append("Ke ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("m.johndoe@gmail.com")
                        }
                    }, textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 20.dp))
        Column(modifier = Modifier.padding(horizontal = 18.dp)) {
            AGInputLayout(label = "Kode") {
                AGInputId(
                    value = "",
                    onValueChange = {},
                    placeholder = "Masukkan Kode OTP",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            AGButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp)
            ) {
                Text(text = "Verifikasi")
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            Text(
                text = TimeUtils.getTimerLabel(timerValue),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Green100
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 40.dp))
    }
}

@Composable
@Preview
fun EmailVerificationContentPreview() {
    AgrimateTheme {
        EmailVerificationContent(timerValue = 0)
    }
}