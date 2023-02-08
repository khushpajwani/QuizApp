package com.app.quizapp.composites

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.quizapp.NavigationRoutes.Routes

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Routes.SplashScreen.route) {
        // Splash Screen
        composable(Routes.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        // Login Screen
        composable(Routes.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
    }
}
