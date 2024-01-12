package com.codelabs.agrimate.screens.shared.getstarted

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.components.AGButton
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.Green100
import com.codelabs.agrimate.ui.theme.Green500
import com.codelabs.agrimate.ui.theme.GreyScale600
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

internal data class GetStartedData(val image: Int, val title: String, val desc: String)

private val getStartedData = listOf(
    GetStartedData(
        R.drawable.onboard_1_picture,
        "Asisten terbaik untuk pertanian Anda",
        "Kelola aktivitas tani jadi lebih mudah dengan fitur penunjang yang otomatis"
    ),
    GetStartedData(
        R.drawable.onboard_2_picture,
        "Dapatkan hasil tani yang berkualitas",
        "Best solution for your lorem ipsum dolor sit amet "
    ),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { getStartedData.size })

    val systemUiController = rememberSystemUiController()

    suspend fun handleChangeActivePage(pageNumber: Int) {
        pagerState.animateScrollToPage(pageNumber)
    }

    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = false)
        systemUiController.setNavigationBarColor(color = Green500, darkIcons = false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Green500)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(1f),
                    painter = painterResource(id = R.drawable.onboard_ellipse_bg),
                    contentDescription = null
                )
                Crossfade(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(2f),
                    targetState = pagerState.currentPage, animationSpec = tween(1000),
                    label = "page_image"
                ) { targetState ->
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .zIndex(2f),
                        painter = painterResource(getStartedData[targetState].image),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 39.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 12.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                repeat(2) { index ->
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(5.dp)
                            .background(
                                color = if (index == pagerState.currentPage) Color.White else Green100,
                                shape = RoundedCornerShape(size = 20.dp)
                            )
                            .clickable {
                                coroutineScope.launch {
                                    handleChangeActivePage(index)
                                }
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 38.dp))
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 30.sp,
                        lineHeight = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        text = getStartedData[index].title
                    )
                    Spacer(modifier = Modifier.padding(bottom = 21.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        lineHeight = (27.3).sp,
                        fontWeight = FontWeight.Medium,
                        color = GreyScale600,
                        textAlign = TextAlign.Center,
                        text = getStartedData[index].desc
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 24.dp),
        ) {
            AGButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (pagerState.currentPage < (getStartedData.size - 1)) {
                        coroutineScope.launch {
                            handleChangeActivePage(pagerState.currentPage + 1)
                        }
                    } else {
                        navController.currentDestination?.id?.let {
                            navController.popBackStack(
                                it,
                                true
                            )
                        }
                        navController.navigate(AGRoute.Auth.SignIn.route)
                    }
                },
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage < (getStartedData.size - 1)) "Selanjutnya" else "Mulai",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}