package com.example.login_signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.login_signup.ui.theme.LoginSignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSignupTheme {
                LoginScreen()
            }
        }
    }
}