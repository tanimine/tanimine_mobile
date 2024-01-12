package com.codelabs.agrimate.screens.farmer.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codelabs.agrimate.R
import com.codelabs.agrimate.ui.common.menu.farmerNavBarMenu
import com.codelabs.agrimate.ui.components.AGDivider
import com.codelabs.agrimate.ui.components.AGExploreMenu
import com.codelabs.agrimate.ui.components.AGNavBarMenuItem
import com.codelabs.agrimate.ui.components.AGNavbar
import com.codelabs.agrimate.ui.components.AGNewsCard
import com.codelabs.agrimate.ui.components.AGWeatherInformation
import com.codelabs.agrimate.ui.components.model.ExploreMenuModel
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.Green300
import com.codelabs.agrimate.ui.theme.Green500
import com.codelabs.agrimate.ui.theme.GreyScale100
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val exploreMenu = listOf(
    ExploreMenuModel(
        R.drawable.ag_plan_icon,
        "Bantu Tanam",
        AGRoute.Farmer.Main.Home.Explorer.HelpPlant.route
    ),
    ExploreMenuModel(
        R.drawable.ag_harvest_icon,
        "Bantu Panen",
        AGRoute.Farmer.Main.Home.Explorer.HelpHarvest.route
    ),
    ExploreMenuModel(
        R.drawable.ag_money_icon,
        "Pemodalan",
        AGRoute.Farmer.Main.Home.Explorer.Capital.route
    ),
    ExploreMenuModel(
        R.drawable.ag_discussion_icon,
        "Diskusi",
        AGRoute.Farmer.Main.Home.Explorer.Discussion.route
    ),
)

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val systemUiController = rememberSystemUiController()
    val scrollState = rememberScrollState()
    var containerColor by remember { mutableStateOf(Green500) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    LaunchedEffect(scrollState.value) {
        systemUiController.setStatusBarColor(
            color = if (scrollState.value > 0) Green500 else Color.Transparent,
            darkIcons = false
        )
        containerColor = if (scrollState.value > 0) Color.White else Green500
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            AGNavbar(modifier = Modifier.fillMaxWidth()) {
                farmerNavBarMenu.forEach { menu ->
                    AGNavBarMenuItem(
                        modifier = Modifier.weight(1f),
                        data = menu,
                        onClick = {
                            navController.navigate(menu.link) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selected = currentDestination?.hierarchy?.any() { it.route == menu.link } == true)
                }
            }
        }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .verticalScroll(scrollState),
            navigateToNotification = { navController.navigate(AGRoute.Farmer.Main.Notification.route) },
            navigateToNews = { navController.navigate(AGRoute.Farmer.Main.News.route) },
            navigateToWeatherForecast = { navController.navigate(AGRoute.Farmer.Main.WeatherForecast.route) },
            navigateToNewsDetail = { navController.navigate(AGRoute.Farmer.Main.News.Detail.route) },
            navigateToOther = { link -> navController.navigate(link) }
        )
    }

}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    navigateToNotification: () -> Unit,
    navigateToNews: () -> Unit,
    navigateToWeatherForecast: () -> Unit,
    navigateToNewsDetail: (String) -> Unit,
    navigateToOther: (String) -> Unit,
) {
    Box(modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(-1f),
            painter = painterResource(id = R.drawable.home_banner_layer),
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
            ) {
                Spacer(modifier = Modifier.padding(bottom = 53.dp))
                HomeHeading(navigateToNotification = navigateToNotification)
                Spacer(modifier = Modifier.padding(bottom = 21.dp))
                AGWeatherInformation(onClick = navigateToWeatherForecast)
                Spacer(modifier = Modifier.padding(bottom = 19.dp))
            }
            HomeMainContent(
                navigateToNews = navigateToNews,
                navigateToNewsDetail = navigateToNewsDetail,
                navigateToOther = navigateToOther
            )
        }
    }
}

@Composable
private fun HomeHeading(navigateToNotification: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeTitle()
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Green300)
                .padding(10.dp)
                .clickable {
                    navigateToNotification()
                },
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "notifikasi",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun HomeTitle() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Hallo Pak Farid!",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = "Rabu 24, Mei 2023",
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun HomeMainContent(
    navigateToNews: () -> Unit,
    navigateToNewsDetail: (String) -> Unit,
    navigateToOther: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
            .background(
                Color.White,
                RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)
            )
            .padding(top = 24.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.padding(horizontal = 18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    exploreMenu.forEach { menu ->
                        AGExploreMenu(menu = menu, onClick = {
                            navigateToOther(menu.link)
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
            AGDivider()
            Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 24.dp)) {
                HomeSectionTitle(navigateToNews)
                Spacer(modifier = Modifier.padding(bottom = 18.dp))
                Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                    repeat(3) {
                        AGNewsCard(onClick = navigateToNewsDetail)
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeSectionTitle(navigateToNews: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Berita Terkini",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = GreyScale100
        )
        Row(modifier = Modifier.clickable {
            navigateToNews()
        }, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Selengkapnya",
                color = Color(0xFF598AD0),
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                modifier = Modifier.size(13.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color(0xFF598AD0)
            )
        }
    }
}