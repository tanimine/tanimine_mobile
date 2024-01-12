package com.codelabs.agrimate.screens.farmer.newsdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.effect.GreenStatusBarEffect
import com.codelabs.agrimate.ui.theme.GreyScale200
import com.codelabs.agrimate.ui.theme.GreyScale500

@Composable
fun NewsDetailScreen(modifier: Modifier = Modifier, navController: NavController) {
    GreenStatusBarEffect()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(onNavigationClick = {
                navController.popBackStack()
            })
        },
        contentWindowInsets = WindowInsets(bottom = 0.dp)
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    painter = painterResource(R.drawable.news_image_dummy),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Column(Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Sumber: Kompas.com", style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 21.3.sp,
                                fontWeight = FontWeight.Medium,
                                color = GreyScale500,
                            )
                        )
                        Text(
                            "Hari ini jam 12.23", style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 21.3.sp,
                                fontWeight = FontWeight.Medium,
                                color = GreyScale500,
                            )
                        )
                    }
                    Spacer(Modifier.padding(bottom = 13.dp))
                    Text(
                        "Ancaman Krisis Pangan", style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = GreyScale200,
                        )
                    )
                    Spacer(Modifier.padding(bottom = 13.dp))
                    Text(
                        "Lorem ipsum", style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 27.3.sp,
                            fontWeight = FontWeight.Medium,
                            color = GreyScale500,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
) {
    AGTopAppBar(
        modifier = modifier,
        navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }
    ) {
        AGTopAppBarDefaultTitle(text = "Berita")
    }
}