package com.ab.shoesy.ui.screen.auth.screen.create_account

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.FacebookOutlinedButton
import com.ab.shoesy.ui.composable.GoogleOutlinedButton
import com.ab.shoesy.ui.composable.PasswordRoundedOutlinedTextField
import com.ab.shoesy.ui.composable.RoundedOutlinedTextField
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.theme.ShoesyTheme

@Composable
fun CreateAccountScreen(
    popBackToLogin: () -> Unit
) {
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
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = stringResource(R.string.please_sign_up_to_your_shoesy_account),
                style = MaterialTheme.typography.bodyMedium
            )
            VerticalSpacer(space = 60)
            RoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onChange = { value -> email = value},
                placeholderText = stringResource(R.string.full_name),
            )
            VerticalSpacer(space = 16)
            RoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onChange = { value -> email = value},
                placeholderText = stringResource(R.string.email),
                keyboardType = KeyboardType.Email
            )
            VerticalSpacer(space = 16)
            PasswordRoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onChange = { value -> password = value},
                placeholderText = stringResource(R.string.password),
                passwordVisible = showPassword,
                onChangePasswordVisibility = { show -> showPassword = show }
            )
            VerticalSpacer(space = 16)
            PasswordRoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onChange = { value -> password = value},
                placeholderText = stringResource(R.string.confirm_password),
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
                Text(text = stringResource(R.string.create_account).uppercase())
            }
            VerticalSpacer(space = 16)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GoogleOutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {}
                )
                FacebookOutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = {}
                )
            }
            VerticalSpacer(space = 8)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.have_account),
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(
                    modifier = Modifier
                        .height(50.dp),
                    onClick = dropUnlessResumed(block = popBackToLogin)
                ) {
                    Text(text = stringResource(R.string.sign_in))
                }
            }
        }
    }
}


@Preview
@Composable
private fun CreateAccountScreenLightPreview() {
    ShoesyTheme {
        CreateAccountScreen(popBackToLogin = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CreateAccountScreenDarkPreview() {
    ShoesyTheme {
        CreateAccountScreen(popBackToLogin = {})
    }
}