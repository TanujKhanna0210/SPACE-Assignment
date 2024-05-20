package com.example.login_signup

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_signup.ui.theme.LoginSignupTheme
import com.example.login_signup.util.Routes
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSignupTheme {
                AppNavigation(activity = this)
            }
        }
    }
}

@Composable
fun AppNavigation(activity: Activity) {

    val navController = rememberNavController()
    val navHost = NavHost(
        navController = navController,
        startDestination = if (FirebaseAuth.getInstance().currentUser != null) Routes.HOME_SCREEN else Routes.LOGIN_SCREEN
    ) {
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(navController)
        }
        composable(Routes.SIGNUP_SCREEN) {
            SignupScreen(navController)
        }
    }

}