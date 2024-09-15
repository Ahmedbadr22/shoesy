package com.ab.shoesy.ui.screen.auth.screen.splash

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ab.core.base.ViewSideEffect
import com.ab.core.constants.App.SPLASH_DELAY_TIME
import com.ab.shoesy.R
import com.ab.shoesy.ui.theme.ShoesyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    effect: Flow<ViewSideEffect>,
    onNavigateToLogin: () -> Unit,
    onNavigateToMain: () -> Unit,
) {

    LaunchedEffect(Unit) {
        delay(SPLASH_DELAY_TIME)
        effect.collectLatest { effect ->
            when(effect) {
                is SplashContract.SideEffects.NavigateToLoginScreen -> onNavigateToLogin()
                is SplashContract.SideEffects.NavigateToMainScreen -> onNavigateToMain()
            }
        }
    }

    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenLightPreview() {
    ShoesyTheme {
        SplashScreen(
            onNavigateToLogin = {},
            onNavigateToMain = {},
            effect = emptyFlow()
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SplashScreenDarkPreview() {
    ShoesyTheme {
        SplashScreen(
            onNavigateToLogin = {},
            onNavigateToMain = {},
            effect = emptyFlow()
        )
    }
}