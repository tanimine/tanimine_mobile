package com.codelabs.agrimate.screens.farmer.planthealth

import androidx.camera.core.AspectRatio
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.components.AGCheckDiseaseResultSheet
import com.codelabs.agrimate.ui.components.CameraPreview
import com.codelabs.agrimate.ui.theme.RedStatus
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckDiseaseScreen(modifier: Modifier = Modifier, navController: NavController) {
    val systemUiController = rememberSystemUiController()

    var openBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = Color.Black,
            darkIcons = false
        )
    }

    Box(
        modifier.windowInsetsPadding(WindowInsets(bottom = 0.dp, top = 0.dp))
    ) {
        CheckDiseaseContent(modifier = Modifier, onSuccessTakeImage = { openBottomSheet = true })
    }

    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(bottom = 0),
            containerColor = Color.White
        ) {
            AGCheckDiseaseResultSheet(modifier = Modifier)
        }
    }
}

@Composable
fun CheckDiseaseContent(modifier: Modifier = Modifier, onSuccessTakeImage: () -> Unit) {
    val imageCapture by remember {
        mutableStateOf(
            ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetAspectRatio(AspectRatio.RATIO_4_3).build()
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.weight(1f)) {
                CameraPreview(modifier = Modifier, imageCapture = imageCapture)
                Image(
                    modifier = Modifier
                        .padding(horizontal = 55.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ag_camera_target_outline),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 29.dp, start = 55.dp, end = 55.dp),
                    text = "Posisikan sejajar dengan tumbuhan yang akan di diagnosis",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Column {
                CameraControls(modifier = Modifier, onTakeImage = { onSuccessTakeImage() })
            }
        }
    }
}

@Composable
private fun CameraControls(modifier: Modifier = Modifier, onTakeImage: () -> Unit) {
    Surface(color = Color.Black, modifier = modifier) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 139.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(53.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(47.dp)
                    .aspectRatio(1f)
                    .clickable { /*TODO*/ },
                painter = painterResource(id = R.drawable.icon_gallery),
                contentDescription = null,
                tint = Color.White
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(RedStatus)
                    .border(4.dp, Color.White, RoundedCornerShape(100))
                    .size(81.dp)
                    .clickable { onTakeImage() },
            )
            Icon(
                modifier = Modifier
                    .size(47.dp)
                    .aspectRatio(1f)
                    .clickable { /*TODO*/ },
                painter = painterResource(id = R.drawable.icon_help),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

