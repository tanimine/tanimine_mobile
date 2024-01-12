package com.codelabs.agrimate.screens.farmer.helpplan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelabs.agrimate.ui.components.AGFeatureCard
import com.codelabs.agrimate.ui.components.AGTopAppBar
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultNavigationIcon
import com.codelabs.agrimate.ui.components.AGTopAppBarDefaultTitle
import com.codelabs.agrimate.ui.navigation.AGRoute
import com.codelabs.agrimate.ui.theme.Green500
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HelpPlanScreen(modifier: Modifier = Modifier, navController: NavController) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Green500, darkIcons = false)
    }

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(onNavigationClick = { navController.popBackStack() }) }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 19.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(2) {
                    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        repeat(2) {
                            AGFeatureCard(
                                modifier = Modifier.weight(1f),
                                onClick = { navController.navigate(AGRoute.Farmer.Main.Home.Explorer.HelpPlant.PlantHealth.route) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar(onNavigationClick: () -> Unit) {
    AGTopAppBar(navigationIcon = { AGTopAppBarDefaultNavigationIcon(onClick = onNavigationClick) }) {
        AGTopAppBarDefaultTitle(text = "Bantu Tanam")
    }
}