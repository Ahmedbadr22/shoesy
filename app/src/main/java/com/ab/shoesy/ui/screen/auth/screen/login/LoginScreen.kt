package com.ab.shoesy.ui.screen.auth.screen.login

import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.core.base.ViewSideEffect
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.FacebookOutlinedButton
import com.ab.shoesy.ui.composable.GoogleOutlinedButton
import com.ab.shoesy.ui.composable.PasswordRoundedOutlinedTextField
import com.ab.shoesy.ui.composable.RoundedOutlinedTextField
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.theme.ShoesyTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun LoginScreen(
    uiState: LoginContract.State,
    onEvent: (LoginContract.Event) -> Unit,
    sideEffects: Flow<ViewSideEffect>,
    onNavigateToCreateAccount: () -> Unit,
    onNavigateToMain: () -> Unit,
    onShowErrorDialog: (Int) -> Unit
) {

    LaunchedEffect(Unit) {
        sideEffects.collectLatest {
            when(it) {
                is LoginContract.SideEffects.ShowErrorDialog -> onShowErrorDialog(it.resId)
                is LoginContract.SideEffects.NavigateToMain -> onNavigateToMain()
            }
        }
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
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = stringResource(R.string.please_sign_in_to_your_shoesy_account),
                style = MaterialTheme.typography.bodyMedium
            )
            VerticalSpacer(space = 60)
            RoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.email,
                onChange = { value -> onEvent(LoginContract.Event.OnEmailChange(value)) },
                placeholderText = stringResource(R.string.email),
                keyboardType = KeyboardType.Email,
                errorMsgResId = uiState.emailErrorResIdMessage
            )
            VerticalSpacer(space = 16)
            PasswordRoundedOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                onChange = { value -> onEvent(LoginContract.Event.OnPasswordChange(value)) },
                placeholderText = stringResource(R.string.password),
                passwordVisible = uiState.showPassword,
                errorMsgResId = uiState.passwordErrorResIdMessage,
                onChangePasswordVisibility = { show -> onEvent(
                    LoginContract.Event.OnShowPasswordStateChange(
                        show
                    )
                ) }
            )
            VerticalSpacer(weight = 1f)
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    onEvent(LoginContract.Event.OnLogin)
                }
            ) {
                Text(text = stringResource(R.string.sign_in).uppercase())
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
                    text = stringResource(R.string.join_with_us),
                    style = MaterialTheme.typography.bodySmall
                )
                TextButton(
                    modifier = Modifier
                        .height(50.dp),
                    onClick = onNavigateToCreateAccount
                ) {
                    Text(text = stringResource(R.string.create_account))
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenLightPreview() {
    ShoesyTheme {
        LoginScreen(
            uiState = LoginContract.State(),
            onEvent = {},
            sideEffects = emptyFlow(),
            onNavigateToCreateAccount = {},
            onShowErrorDialog = {},
            onNavigateToMain = {},
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenDarkPreview() {
    ShoesyTheme {
        LoginScreen(
            uiState = LoginContract.State(),
            onEvent = {},
            sideEffects = emptyFlow(),
            onNavigateToCreateAccount = {},
            onShowErrorDialog = {},
            onNavigateToMain = {},
        )
    }
}