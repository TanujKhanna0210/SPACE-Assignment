package com.example.login_signup.data

sealed class LoginUIEvent {

    data class EmailChanged(var email: String): LoginUIEvent()
    data class PasswordChanged(var password: String): LoginUIEvent()

    object LoginButtonClicked: LoginUIEvent()
}