package com.example.login_signup.data

data class RegistrationUIState(
    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false

)
