package com.codelabs.agrimate.ui.navigation.farmer.other

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.codelabs.agrimate.screens.farmer.capital.CapitalScreen
import com.codelabs.agrimate.screens.farmer.discussion.DiscussionScreen
import com.codelabs.agrimate.screens.farmer.helpharvest.HelpHarvestScreen
import com.codelabs.agrimate.screens.farmer.helpplan.HelpPlanScreen
import com.codelabs.agrimate.screens.farmer.news.NewsScreen
import com.codelabs.agrimate.screens.farmer.newsdetail.NewsDetailScreen
import com.codelabs.agrimate.screens.farmer.notification.NotificationScreen
import com.codelabs.agrimate.screens.farmer.planthealth.CheckDiseaseScreen
import com.codelabs.agrimate.screens.farmer.planthealth.DiseaseDetailScreen
import com.codelabs.agrimate.screens.farmer.planthealth.PlantHealthScreen
import com.codelabs.agrimate.screens.farmer.planthealth.SpreadOfDiseaseScreen
import com.codelabs.agrimate.screens.farmer.weather_forecast.WeatherForecastScreen
import com.codelabs.agrimate.ui.navigation.AGRoute

fun NavGraphBuilder.farmerOtherGraph(navController: NavController) {
    composable(AGRoute.Farmer.Main.Home.Explorer.HelpPlant.route) {
        HelpPlanScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Home.Explorer.HelpHarvest.route) {
        HelpHarvestScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Home.Explorer.Capital.route) {
        CapitalScreen()
    }
    composable(AGRoute.Farmer.Main.Home.Explorer.Discussion.route) {
        DiscussionScreen()
    }
    composable(AGRoute.Farmer.Main.Home.Explorer.HelpPlant.PlantHealth.route) {
        PlantHealthScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Home.Explorer.HelpPlant.PlantHealth.SpreadOfDisease.route) {
        SpreadOfDiseaseScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Home.Explorer.HelpPlant.PlantHealth.CheckDisease.route) {
        CheckDiseaseScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Home.Explorer.HelpPlant.PlantHealth.DiseaseDetail.route) {
        DiseaseDetailScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.News.route) {
        NewsScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.News.Detail.route) {
        NewsDetailScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.Notification.route) {
        NotificationScreen(navController = navController)
    }
    composable(AGRoute.Farmer.Main.WeatherForecast.route) {
        WeatherForecastScreen(navController = navController)
    }
}