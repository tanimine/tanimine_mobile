package com.codelabs.agrimate.ui.navigation.farmer.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codelabs.agrimate.screens.farmer.home.HomeScreen
import com.codelabs.agrimate.screens.farmer.land.LandScreen
import com.codelabs.agrimate.screens.farmer.profile.ProfileScreen
import com.codelabs.agrimate.ui.navigation.AGRoute

fun NavGraphBuilder.farmerMainGraph(navController: NavHostController) {
    composable(AGRoute.Farmer.Main.Home.route) {
        HomeScreen(navController = navController)
    }

    composable(AGRoute.Farmer.Main.Land.route) {
        LandScreen(navController = navController)
    }

    composable(AGRoute.Farmer.Main.Profile.route) {
        ProfileScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Chat.route) {

    }
}