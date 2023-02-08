package com.app.quizapp.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.app.quizapp.activities.ui.theme.QuizAppTheme
import com.app.quizapp.composites.LoginScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : BaseActivity() {

    private var keep: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { keep }
            runBlocking {
                delay(3000)
                keep = false
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
                ) {
                    val navController = rememberNavController()
                    LoginScreen(navController)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    QuizAppTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}