package com.codelabs.agrimate.screens.farmer.land

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codelabs.agrimate.ui.common.menu.farmerNavBarMenu
import com.codelabs.agrimate.ui.components.AGInputSearch
import com.codelabs.agrimate.ui.components.AGLandCard
import com.codelabs.agrimate.ui.components.AGNavBarMenuItem
import com.codelabs.agrimate.ui.components.AGNavbar
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.effect.GreenStatusBarEffect
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.AgrimateTheme
import com.codelabs.agrimate.ui.theme.Green100

@Composable
fun LandScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    GreenStatusBarEffect()

    Scaffold(
        modifier = modifier,
        topBar = {
            LandTopAppBar()
        },
        floatingActionButton = {
            LandAddButton(onClick = { navController.navigate(AGRoute.Farmer.Main.Land.Add.route) })
        },
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
                        selected = currentDestination?.hierarchy?.any { it.route == menu.link } == true)
                }
            }
        }
    ) { paddingValues ->
        LandContent(
            modifier = Modifier.padding(paddingValues),
            navigateToLandDetail = {
                navController.navigate(AGRoute.Farmer.Main.Land.Detail.route)
            }
        )
    }

}

@Composable
fun LandContent(modifier: Modifier = Modifier, navigateToLandDetail: (String) -> Unit) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier.padding(
                start = 18.dp,
                end = 18.dp,
                bottom = 18.dp,
                top = 24.dp
            )
        ) {
            AGInputSearch(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {/*TODO*/ },
                placeholder = "Cari Lahan"
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(start = 18.dp, end = 18.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(10) { index ->
                item(key = index) {
                    AGLandCard(onClick = { navigateToLandDetail("$index") })
                }
            }
        }
    }
}

@Composable
private fun LandTopAppBar(
    modifier: Modifier = Modifier,
) {
    AGTopAppBar(
        modifier = modifier,
    ) {
        AGTopAppBarDefaultTitle(text = "Pilih Lahan")
    }
}

@Composable
private fun LandAddButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier, shape = RoundedCornerShape(100),
        color = Color.White,
        onClick = onClick,
        shadowElevation = 4.dp
    ) {
        Box(Modifier.padding(16.dp)) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = Green100)
        }
    }
}

@Composable
@Preview
fun LandContentPreview() {
    AgrimateTheme {
        LandContent(navigateToLandDetail = {})
    }
}