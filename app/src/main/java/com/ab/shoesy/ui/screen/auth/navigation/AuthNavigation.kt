package com.ab.shoesy.ui.screen.auth.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.shoesy.ui.screen.main.navigation.navigateToErrorDialog

import com.ab.shoesy.ui.screen.main.navigation.popUpToLogin
import com.ab.shoesy.ui.screen.auth.screen.create_account.CreateAccountScreen
import com.ab.shoesy.ui.screen.auth.screen.login.LoginContract
import com.ab.shoesy.ui.screen.auth.screen.login.LoginScreen
import com.ab.shoesy.ui.screen.auth.screen.login.LoginViewModel
import com.ab.shoesy.ui.screen.auth.screen.splash.SplashScreen
import com.ab.shoesy.ui.screen.auth.screen.splash.SplashViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AuthNavHost(
    navHostController: NavHostController,
    onNavigateToMainActivity: () -> Unit
) {
    NavHost(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        navController = navHostController,
        startDestination = AuthScreen.SPLASH
    ) {
        composable<AuthScreen.SPLASH>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )
            }
        ) {
            val splashViewModel: SplashViewModel = koinViewModel()

            SplashScreen(
                effect = splashViewModel.effect,
                onNavigateToLogin = navHostController::navigateToLogin,
                onNavigateToMain = onNavigateToMainActivity
            )
        }

        composable<AuthScreen.LOGIN>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )
            }
        ) {
            val loginViewModel: LoginViewModel = koinViewModel()

            val uiState: LoginContract.State by loginViewModel.viewState.collectAsStateWithLifecycle()

            LoginScreen(
                uiState = uiState,
                onEvent = loginViewModel::onEvent,
                sideEffects = loginViewModel.effect,
                onNavigateToCreateAccount = navHostController::navigateToRegistration,
                onNavigateToMain = onNavigateToMainActivity,
                onShowErrorDialog = navHostController::navigateToErrorDialog
            )
        }

        composable<AuthScreen.REGISTRATION> {
            CreateAccountScreen(
                popBackToLogin = navHostController::popUpToLogin
            )
        }
    }
}

fun NavHostController.navigateToLogin() {
    navigate(AuthScreen.LOGIN) {
        popUpTo(AuthScreen.SPLASH) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateToRegistration() {
    navigate(AuthScreen)
}