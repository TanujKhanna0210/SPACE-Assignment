package com.example.login_signup.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.login_signup.data.SignupViewModel
import com.example.login_signup.ui.theme.Black
import com.example.login_signup.ui.theme.BlueGray

@Composable
fun HomeScreen(
    navController: NavController,
    signupViewModel: SignupViewModel = viewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .height(40.dp),
                onClick = {
                    signupViewModel.signOut(navController = navController)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme()) BlueGray else Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Sign Out",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
                )
            }
        }
    }
}