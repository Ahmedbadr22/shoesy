package com.ab.shoesy.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.shoesy.ui.screen.create_account.CreateAccountScreen
import com.ab.shoesy.ui.screen.login.LoginScreen
import com.ab.shoesy.ui.screen.splash.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        navController = navHostController,
        startDestination = Screen.SPLASH
    ) {

        composable<Screen.SPLASH> {
            SplashScreen(
                onDelayFinish = navHostController::navigateToLogin
            )
        }

        composable<Screen.LOGIN> {
            LoginScreen(
                onNavigateToCreateAccount = navHostController::navigateToRegistration
            )
        }

        composable<Screen.REGISTRATION> {
            CreateAccountScreen(
                popBackToLogin = navHostController::popUpToLogin
            )
        }
    }
}

fun NavHostController.navigateToLogin() {
    navigate(Screen.LOGIN) {
        popUpTo(Screen.SPLASH) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateToRegistration() {
    navigate(Screen.REGISTRATION)
}

fun NavHostController.popUpToLogin() {
    popBackStack()
}