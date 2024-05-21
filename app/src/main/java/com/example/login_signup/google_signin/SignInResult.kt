package com.example.login_signup.google_signin

data class SignInResult(
    val data: UserData?,
    val errorMessage:String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?
)