package com.example.login_signup.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.login_signup.data.LoginUIEvent
import com.example.login_signup.data.LoginUIState
import com.example.login_signup.data.rules.Validator
import com.example.login_signup.util.Routes
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG = "LOGIN_VIEW_MODEL"

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    fun onEvent(
        event: LoginUIEvent,
        navController: NavController
    ) {
        validateLoginDataWithRules()
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login(navController = navController)
            }
        }
    }

    private fun validateLoginDataWithRules() {
        val emailResult = Validator.validateEmail(email = loginUIState.value.email)
        val passwordResult = Validator.validatePassword(password = loginUIState.value.password)
        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    private fun login(navController: NavController) {

        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(
                email,
                password
            )
            .addOnCompleteListener {
                Log.d(TAG, "Inside_login_success")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                if (it.isSuccessful) {
                    navController.navigate(Routes.HOME_SCREEN) {
                        popUpTo(Routes.LOGIN_SCREEN) {
                            inclusive = true
                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_login_failure")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }

}