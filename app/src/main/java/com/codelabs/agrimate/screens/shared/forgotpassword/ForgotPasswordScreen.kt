package com.codelabs.agrimate.screens.shared.forgotpassword

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGAlreadyHaveAccount
import com.codelabs.agrimate.ui.components.AGAuthTopBanner
import com.codelabs.agrimate.ui.components.AGButton
import com.codelabs.agrimate.ui.components.AGInputEmail
import com.codelabs.agrimate.ui.components.AGInputLayout
import com.codelabs.agrimate.ui.components.AGMessageDialogSuccess
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.AgrimateTheme
import kotlinx.coroutines.delay

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val navigateToVerification = {
        navController.navigate(AGRoute.Auth.EmailVerification.route) {
            launchSingleTop = true
        }
    }

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    ForgotPasswordContent(
        modifier = modifier,
        navigateToSignin = {
            navController.currentDestination?.id?.let { navController.popBackStack(it, true) }
            navController.navigate(AGRoute.Auth.SignIn.route) {
                popUpTo(AGRoute.Auth.SignIn.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        },
        uiState = uiState,
        onEmailChange = viewModel::setEmail,
        onSendCodeClick = viewModel::forgotPassword
    )

    if (uiState.isLoading) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator()
        }
    }

    if (uiState.isCodeSent) {
        LaunchedEffect(Unit) {
            delay(5000)
            navigateToVerification()
        }
        AGMessageDialogSuccess(
            onDismissRequest = navigateToVerification,
            title = "Kode Sudah Dikirim!",
            description = "Silahkan cek email Anda!"
        )
    }
}

@Composable
fun ForgotPasswordContent(
    modifier: Modifier = Modifier,
    navigateToSignin: () -> Unit,
    uiState: ForgotPasswordUiState,
    onEmailChange: (String) -> Unit,
    onSendCodeClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AGAuthTopBanner(title = "Lupa Password")
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
        ) {
            AGInputLayout(modifier = Modifier.fillMaxWidth(), label = "Email") {
                AGInputEmail(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    placeholder = "Masukkan email anda",
                    imeAction = ImeAction.Next,
                    isError = !uiState.inputEmail.isValid,
                    supportingText = if (!uiState.inputEmail.isValid) {
                        { Text(text = uiState.inputEmail.message) }
                    } else null
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            AGButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSendCodeClick() },
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp),
            ) {
                Text(text = "Kirim Kode")
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            AGAlreadyHaveAccount(navigateToSignin = navigateToSignin)
            Spacer(modifier = Modifier.padding(bottom = 40.dp))
        }
    }
}

@Composable
@Preview
fun ForgotPasswordContentPreview() {
    AgrimateTheme {
        ForgotPasswordContent(
            navigateToSignin = {},
            onSendCodeClick = {},
            onEmailChange = {},
            uiState = ForgotPasswordUiState()
        )
    }
}