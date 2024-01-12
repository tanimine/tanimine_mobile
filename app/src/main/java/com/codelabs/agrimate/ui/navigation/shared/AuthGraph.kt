package com.codelabs.agrimate.ui.navigation.shared

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.codelabs.agrimate.screens.shared.emailverification.EmailVerificationScreen
import com.codelabs.agrimate.screens.shared.forgotpassword.ForgotPasswordScreen
import com.codelabs.agrimate.screens.shared.signin.SignInScreen
import com.codelabs.agrimate.screens.shared.signup.SignupScreen
import com.codelabs.agrimate.screens.shared.signup.collector.SignupCollectorScreen
import com.codelabs.agrimate.screens.shared.signup.farmer.SignupFarmerScreen
import com.codelabs.agrimate.ui.navigation.AGRoute

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(startDestination = AGRoute.Auth.SignIn.route, route = AGRoute.Auth.route) {
        composable(AGRoute.Auth.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(AGRoute.Auth.SignUp.route) {
            SignupScreen(navController = navController)
        }
        composable(AGRoute.Auth.SignUp.Farmer.route) {
            SignupFarmerScreen(navController = navController)
        }
        composable(AGRoute.Auth.SignUp.Collector.route) {
            SignupCollectorScreen(navController = navController)
        }
        composable(AGRoute.Auth.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(AGRoute.Auth.EmailVerification.route) {
            EmailVerificationScreen(navController = navController)
        }
    }
}