package com.ab.shoesy.ui.screen.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.PasswordRoundedOutlinedTextField
import com.ab.shoesy.ui.composable.RoundedOutlinedTextField
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.theme.ShoesyTheme

@Composable
fun LoginScreen() {
    var showPassword by remember {
        mutableStateOf(false)
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .padding(top = 32.dp)
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = "Please sign in to your shoesy account.",
                style = MaterialTheme.typography.bodyMedium
            )
            VerticalSpacer(space = 60)
            RoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onChange = { value -> email = value},
                placeholderText = "Email",
                keyboardType = KeyboardType.Email
            )
            VerticalSpacer(space = 16)
            PasswordRoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onChange = { value -> password = value},
                placeholderText = "Password",
                passwordVisible = showPassword,
                onChangePasswordVisibility = { show -> showPassword = show }
            )
            VerticalSpacer(weight = 1f)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {}
            ) {
                Text(text = "SIGN IN")
            }
            VerticalSpacer(space = 16)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp
                )
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color = Color.Gray.copy(alpha = 0.2f))
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google"
                    )
                    Text(text = "GOOGLE")
                }
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(color = Color.Gray.copy(alpha = 0.2f))
                            .padding(4.dp),
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Google"
                    )
                    Text(text = "FACEBOOK")
                }
            }
            VerticalSpacer(space = 8)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Join with us:",
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(
                    modifier = Modifier
                        .height(50.dp),
                    onClick = {}
                ) {
                    Text(text = "Create Account")
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenLightPreview() {
    ShoesyTheme {
        LoginScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenDarkPreview() {
    ShoesyTheme {
        LoginScreen()
    }
}