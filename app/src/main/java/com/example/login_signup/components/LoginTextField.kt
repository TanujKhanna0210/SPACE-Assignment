package com.example.login_signup.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.login_signup.R
import com.example.login_signup.ui.theme.Black
import com.example.login_signup.ui.theme.focusedTextFieldText
import com.example.login_signup.ui.theme.textFieldContainer
import com.example.login_signup.ui.theme.unfocusedTextFieldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    label: String,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    val text = remember {
        mutableStateOf("")
    }

    TextField(
        modifier = modifier,
        value = text.value,
        onValueChange = {
            text.value = it
            onTextSelected(it)
        },
        keyboardOptions = KeyboardOptions.Default,
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
        ),
        isError = !errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    label: String,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    val password = remember {
        mutableStateOf("")
    }

    val isPasswordVisible = remember {
        mutableStateOf(false)
    }

    TextField(
        modifier = modifier,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
        ),
        trailingIcon = {
            val iconImage =
                if (isPasswordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

            val description =
                if (isPasswordVisible.value) stringResource(R.string.hide_password)
                else stringResource(R.string.show_password)

            IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}