package com.example.login_signup.google_signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
