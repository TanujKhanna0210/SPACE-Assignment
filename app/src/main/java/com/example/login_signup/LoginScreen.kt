package com.example.login_signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login_signup.ui.theme.Black
import com.example.login_signup.ui.theme.BlueGray
import com.example.login_signup.ui.theme.LoginTextField
import com.example.login_signup.ui.theme.Roboto

@Composable
fun LoginScreen() {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {

            TopSection()

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                LoginSection()

                Spacer(modifier = Modifier.height(30.dp))

                GoogleLoginSection()

                val uiColor = if (isSystemInDarkTheme()) Color.White else Black
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF94A3B8),
                                fontSize = 14.sp,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Normal
                            )
                        ) {
                            append("Don't have account?")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = uiColor,
                                fontSize = 14.sp,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Normal
                            )
                        ) {
                            append(" ")
                            append("Create now!")
                        }
                    })
                }
            }

        }
    }
}

@Composable
private fun GoogleLoginSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "or continue with",
            style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF64748B))
        )

        Spacer(modifier = Modifier.height(20.dp))

        GoogleLogin(icon = R.drawable.google, text = "Google") {

        }
    }
}

@Composable
private fun LoginSection() {
    LoginTextField(
        label = "Email",
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(15.dp))

    LoginTextField(
        label = "Password",
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) BlueGray else Black,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Log in",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black

    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.46f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.space),
                style = MaterialTheme.typography.headlineMedium,
                color = uiColor
            )
            Text(
                text = stringResource(id = R.string.for_early_childhood_education),
                style = MaterialTheme.typography.titleMedium,
                color = uiColor
            )
        }

        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor
        )

    }
}