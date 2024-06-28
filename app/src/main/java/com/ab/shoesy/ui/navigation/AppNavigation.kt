package com.ab.shoesy.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.ab.shoesy.ui.composable.ErrorDialog
import com.ab.shoesy.ui.screen.create_account.CreateAccountScreen
import com.ab.shoesy.ui.screen.login.LoginContract
import com.ab.shoesy.ui.screen.login.LoginScreen
import com.ab.shoesy.ui.screen.login.LoginViewModel
import com.ab.shoesy.ui.screen.main.MainScreen
import com.ab.shoesy.ui.screen.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun AppNavHost(
    modifier: Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        navController = navHostController,
        startDestination = Screen.Main
    ) {

        composable<Screen.SPLASH> {
            SplashScreen(
                onDelayFinish = navHostController::navigateToLogin
            )
        }

        composable<Screen.LOGIN> {
            val loginViewModel: LoginViewModel = koinViewModel()

            val uiState: LoginContract.State by loginViewModel.viewState.collectAsStateWithLifecycle()

            LoginScreen(
                uiState = uiState,
                onEvent = loginViewModel::setEvent,
                sideEffects = loginViewModel.effect,
                onNavigateToCreateAccount = navHostController::navigateToRegistration,
                onNavigateToMain = navHostController::navigateToMain,
                onShowErrorDialog = navHostController::navigateToErrorDialog
            )
        }

        composable<Screen.REGISTRATION> {
            CreateAccountScreen(
                popBackToLogin = navHostController::popUpToLogin
            )
        }

        composable<Screen.Main> {
            MainScreen()
        }

        dialog<Screen.ErrorDialog> { backstackEntry ->
            val dialog : Screen.ErrorDialog = backstackEntry.toRoute()
            ErrorDialog(
                message = dialog.message
            ) {
                navHostController.navigateUp()
            }
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

fun NavHostController.navigateToMain() {
    navigate(Screen.Main)
}

fun NavHostController.navigateToErrorDialog(resMessage: Int) {
    navigate(Screen.ErrorDialog(resMessage))
}

fun NavHostController.popUpToLogin() {
    popBackStack()
}