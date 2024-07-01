package com.ab.shoesy.ui.screen.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.shoesy.ui.screen.cart.CartScreen
import com.ab.shoesy.ui.screen.favorite.FavoriteScreen
import com.ab.shoesy.ui.screen.home.HomeContract
import com.ab.shoesy.ui.screen.home.HomeScreen
import com.ab.shoesy.ui.screen.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainNavHost(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = MainBottomTabs.Home.route) {
        composable(route = MainBottomTabs.Home.route) {
            val homeViewModel: HomeViewModel = koinViewModel()

            val uiState: HomeContract.State by homeViewModel.viewState.collectAsStateWithLifecycle()

            HomeScreen(
                paddingValues = paddingValues,
                uiState = uiState,
                onEvent = homeViewModel::onEvent,
            )
        }

        composable(route = MainBottomTabs.Favorite.route) {
            FavoriteScreen()
        }

        composable(route = MainBottomTabs.Cart.route) {
            CartScreen()
        }
    }
}