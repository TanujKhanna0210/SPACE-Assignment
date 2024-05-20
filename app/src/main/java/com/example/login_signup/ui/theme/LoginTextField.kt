package com.example.login_signup.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    label: String
) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black

    TextField(
        modifier = modifier,
        value = "",
        onValueChange = {},
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = uiColor
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            focusedLabelColor = MaterialTheme.colorScheme.focusedTextFieldText,
            unfocusedBorderColor = MaterialTheme.colorScheme.textFieldContainer,
            focusedBorderColor = MaterialTheme.colorScheme.textFieldContainer
        )
    )
}