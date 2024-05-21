package com.example.login_signup

import android.app.Application
import com.google.firebase.FirebaseApp

class LoginSignupApp: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }

}