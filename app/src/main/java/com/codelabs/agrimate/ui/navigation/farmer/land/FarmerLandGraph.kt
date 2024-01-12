package com.codelabs.agrimate.ui.navigation.farmer.land

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.codelabs.agrimate.screens.farmer.landadd.LandAddScreen
import com.codelabs.agrimate.screens.farmer.landdetail.LandDetailScreen
import com.codelabs.agrimate.screens.farmer.landdetailactivity.LandDetailActivityScreen
import com.codelabs.agrimate.ui.navigation.AGRoute

fun NavGraphBuilder.farmerLandGraph(navController: NavController) {
    composable(AGRoute.Farmer.Main.Land.Add.route) {
        LandAddScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Land.Detail.route) {
        LandDetailScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Land.Detail.Activity.Detail.route) {
        LandDetailActivityScreen(navController = navController)
    }
}