package com.example.login_signup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_signup.google_signin.GoogleAuthUIClient
import com.example.login_signup.google_signin.GoogleSignInViewModel
import com.example.login_signup.screens.HomeScreen
import com.example.login_signup.screens.LoginScreen
import com.example.login_signup.screens.SignupScreen
import com.example.login_signup.ui.theme.LoginSignupTheme
import com.example.login_signup.util.Routes
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUIClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSignupTheme {
                val navController = rememberNavController()
                val navHost = NavHost(
                    navController = navController,
                    startDestination = if (FirebaseAuth.getInstance().currentUser != null) Routes.HOME_SCREEN else Routes.LOGIN_SCREEN
                ) {
                    composable(Routes.LOGIN_SCREEN) {
                        val viewModel = viewModel<GoogleSignInViewModel>()
                        val state by viewModel.state.collectAsState()

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate(Routes.HOME_SCREEN) {
                                    popUpTo(Routes.LOGIN_SCREEN) {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        LoginScreen(
                            navController = navController,
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            })
                    }
                    composable(Routes.SIGNUP_SCREEN) {
                        val viewModel = viewModel<GoogleSignInViewModel>()
                        val state by viewModel.state.collectAsState()

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate(Routes.HOME_SCREEN) {
                                    popUpTo(Routes.SIGNUP_SCREEN) {
                                        inclusive = true
                                    }
                                }
                            }
                        }

                        SignupScreen(
                            navController = navController,
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            })
                    }
                    composable(Routes.HOME_SCREEN) {
                        HomeScreen(navController)
                    }
                }
            }
        }
    }
}