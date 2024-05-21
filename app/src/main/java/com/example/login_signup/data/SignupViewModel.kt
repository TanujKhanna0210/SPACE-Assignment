package com.example.login_signup.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.login_signup.data.rules.Validator
import com.example.login_signup.util.Routes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignupViewModel : ViewModel() {

    private val TAG = "SIGNUP_VIEW_MODEL"

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent, navController: NavController) {
        validateDataWithRules()
        when (event) {
            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }

            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

            is SignupUIEvent.SignupButtonClicked -> {
                signUp(navController = navController)
            }
        }
    }

    private fun signUp(navController: NavController) {

        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password,
            navController = navController
        )
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(email = registrationUIState.value.email)
        val passwordResult = Validator.validatePassword(password = registrationUIState.value.password)

        registrationUIState.value = registrationUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    private fun createUserInFirebase(email: String, password: String, navController: NavController) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                if(it.isSuccessful) {
                    navController.navigate(Routes.HOME_SCREEN) {
                        popUpTo(Routes.SIGNUP_SCREEN) {
                            inclusive = true
                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }

    fun signOut(navController: NavController) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val authStateListener = AuthStateListener {
            if(it.currentUser == null) {
                Log.d(TAG, "Sign Out is successful")
                navController.navigate(Routes.SIGNUP_SCREEN) {
                    popUpTo(Routes.HOME_SCREEN) {
                        inclusive = true
                    }
                }
            } else {
                Log.d(TAG, "Sign Out failed")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)
    }

}