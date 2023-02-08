package com.app.quizapp.activities

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.quizapp.NavigationRoutes.Routes
import com.app.quizapp.activities.ui.theme.QuizAppTheme

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
            LoginScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizAppTheme {
        LoginScreen()
    }
}
