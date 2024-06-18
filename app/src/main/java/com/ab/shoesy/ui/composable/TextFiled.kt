package com.ab.shoesy.ui.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.ab.shoesy.R


@Composable
fun RoundedOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    placeholderText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMsgResId: Int = 0
) {
    OutlinedTextField(
        shape = RoundedCornerShape(25),
        modifier = modifier,
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = errorMsgResId != 0,
        supportingText = {
            if (errorMsgResId != 0) {
                Text(text = stringResource(id = errorMsgResId))
            }
        },
        label = {
            if (placeholderText != null) {
                Text(text = placeholderText)
            }
        },
        singleLine = true,
        onValueChange = onChange
    )
}

@Composable
fun PasswordRoundedOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    placeholderText: String? = null,
    passwordVisible: Boolean = false,
    onChangePasswordVisibility: (Boolean) -> Unit,
    errorMsgResId: Int = 0
) {
    OutlinedTextField(
        shape = RoundedCornerShape(25),
        modifier = modifier,
        value = value,
        label = {
            if (placeholderText != null) {
                Text(text = placeholderText)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible) painterResource(id = R.drawable.eye)
            else painterResource(id = R.drawable.eye_closed)

            IconButton(onClick = { onChangePasswordVisibility(!passwordVisible) }) {
                Icon(painter = icon, contentDescription = "Password Visibility")
            }
        },
        isError = errorMsgResId != 0,
        supportingText = {
            if (errorMsgResId != 0) {
                Text(text = stringResource(id = errorMsgResId))
            }
        },
        onValueChange = onChange
    )
}