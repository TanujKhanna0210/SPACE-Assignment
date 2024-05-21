package com.example.login_signup.data

sealed class SignupUIEvent {

    data class EmailChanged(var email: String): SignupUIEvent()
    data class PasswordChanged(var password: String): SignupUIEvent()

    object SignupButtonClicked: SignupUIEvent()

}
