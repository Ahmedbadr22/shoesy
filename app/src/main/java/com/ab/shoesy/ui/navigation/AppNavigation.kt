package com.ab.shoesy.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.ab.shoesy.ui.composable.ErrorDialog
import com.ab.shoesy.ui.screen.brand.BrandContract
import com.ab.shoesy.ui.screen.brand.BrandScreen
import com.ab.shoesy.ui.screen.brand.BrandViewModel
import com.ab.shoesy.ui.screen.brands.BrandsScreen
import com.ab.shoesy.ui.screen.create_account.CreateAccountScreen
import com.ab.shoesy.ui.screen.login.LoginContract
import com.ab.shoesy.ui.screen.login.LoginScreen
import com.ab.shoesy.ui.screen.login.LoginViewModel
import com.ab.shoesy.ui.screen.main.MainScreen
import com.ab.shoesy.ui.screen.shoe.ShoeContract
import com.ab.shoesy.ui.screen.shoe.ShoeScreen
import com.ab.shoesy.ui.screen.shoe.ShoeViewModel
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
        startDestination = Screen.SPLASH
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
                onEvent = loginViewModel::onEvent,
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

        composable<Screen.Brand> { backstackEntry ->
            val brand : Screen.Brand = backstackEntry.toRoute()

            val brandViewModel: BrandViewModel = koinViewModel()

            LaunchedEffect(Unit) {
                brandViewModel.onEvent(BrandContract.Event.ListShoesByBrandId(brand.id))
            }

            val uiState: BrandContract.State by brandViewModel.viewState.collectAsStateWithLifecycle()

            BrandScreen(
                brand = brand.toData(),
                uiState = uiState,
                onEvent = brandViewModel::onEvent
            )
        }

        composable<Screen.Brands> {
            val brandViewModel: BrandViewModel = koinViewModel()

            LaunchedEffect(Unit) {
                brandViewModel.onEvent(BrandContract.Event.ListBrands)
            }

            val uiState: BrandContract.State by brandViewModel.viewState.collectAsStateWithLifecycle()

            BrandsScreen(
                uiState = uiState,
                onEvent = brandViewModel::onEvent
            )
        }

        composable<Screen.ShoeDetail> { backstackEntry ->
            val shoeDetail : Screen.ShoeDetail = backstackEntry.toRoute()

            val shoeViewModel: ShoeViewModel = koinViewModel()

            LaunchedEffect(Unit) {
                shoeViewModel.onEvent(ShoeContract.Event.GetShoeById(shoeDetail.shoeId))
            }

            val uiState: ShoeContract.State by shoeViewModel.viewState.collectAsStateWithLifecycle()

            ShoeScreen(
                uiState = uiState,
                onEvent = shoeViewModel::onEvent
            )
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