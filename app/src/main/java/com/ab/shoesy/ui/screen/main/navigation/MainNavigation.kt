package com.ab.shoesy.ui.screen.main.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.ab.shoesy.ui.composable.ErrorDialog
import com.ab.shoesy.ui.screen.main.screen.brand.BrandContract
import com.ab.shoesy.ui.screen.main.screen.brand.BrandScreen
import com.ab.shoesy.ui.screen.main.screen.brand.BrandViewModel
import com.ab.shoesy.ui.screen.main.screen.brands.BrandsScreen
import com.ab.shoesy.ui.screen.main.screen.cart.CartContract
import com.ab.shoesy.ui.screen.main.screen.cart.CartScreen
import com.ab.shoesy.ui.screen.main.screen.cart.CartViewModel
import com.ab.shoesy.ui.screen.main.screen.favorite.FavoriteContract
import com.ab.shoesy.ui.screen.main.screen.favorite.FavoriteScreen
import com.ab.shoesy.ui.screen.main.screen.favorite.FavoriteViewModel
import com.ab.shoesy.ui.screen.main.screen.home.HomeContract
import com.ab.shoesy.ui.screen.main.screen.home.HomeScreen
import com.ab.shoesy.ui.screen.main.screen.home.HomeViewModel
import com.ab.shoesy.ui.screen.main.screen.profile.ProfileScreen
import com.ab.shoesy.ui.screen.main.screen.shoe.ShoeContract
import com.ab.shoesy.ui.screen.main.screen.shoe.ShoeScreen
import com.ab.shoesy.ui.screen.main.screen.shoe.ShoeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier.background(color = MaterialTheme.colorScheme.background),
        navController = navHostController,
        startDestination = Screen.Home
    ) {

        animatedComposable<Screen.Home> {
            val homeViewModel: HomeViewModel = koinViewModel()

            val uiState: HomeContract.State by homeViewModel.viewState.collectAsStateWithLifecycle()

            HomeScreen(
                uiState = uiState,
                onEvent = homeViewModel::onEvent
            )
        }

        animatedComposable<Screen.Favorites> {
            val favoriteViewModel: FavoriteViewModel = koinViewModel()

            val uiState: FavoriteContract.State by favoriteViewModel.viewState.collectAsStateWithLifecycle()

            FavoriteScreen(
                uiState = uiState,
                onEvent = favoriteViewModel::onEvent
            )
        }

        animatedComposable<Screen.Cart> {
            val cartViewModel: CartViewModel = koinViewModel()

            val uiState: CartContract.State by cartViewModel.viewState.collectAsStateWithLifecycle()

            CartScreen(
                uiState = uiState,
                onEvent = cartViewModel::onEvent
            )
        }

        animatedComposable<Screen.Profile> {

            ProfileScreen()
        }

        animatedComposable<Screen.Brand> { backstackEntry ->
            val brand: Screen.Brand = backstackEntry.toRoute()

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

        animatedComposable<Screen.Brands> {
            val brandViewModel: BrandViewModel = koinViewModel()

            val uiState: BrandContract.State by brandViewModel.viewState.collectAsStateWithLifecycle()

            BrandsScreen(
                uiState = uiState,
                onEvent = brandViewModel::onEvent
            )
        }


        animatedComposable<Screen.ShoeDetail> { backstackEntry ->
            val shoeDetail: Screen.ShoeDetail = backstackEntry.toRoute()

            val shoeViewModel: ShoeViewModel = koinViewModel()

            LaunchedEffect(Unit) {
                shoeViewModel.onEvent(ShoeContract.Event.GetShoeById(shoeDetail.shoeId))
            }

            val uiState: ShoeContract.State by shoeViewModel.viewState.collectAsStateWithLifecycle()

            ShoeScreen(
                uiState = uiState,
                sideEffects = shoeViewModel.effect,
                onEvent = shoeViewModel::onEvent
            )
        }



        dialog<Screen.ErrorDialog> { backstackEntry ->
            val dialog: Screen.ErrorDialog = backstackEntry.toRoute()
            ErrorDialog(
                message = dialog.message
            ) {
                navHostController.navigateUp()
            }
        }
    }
}


inline fun <reified R : Any> NavGraphBuilder.animatedComposable(
    duration: Int = 700,
    crossinline content: @Composable (NavBackStackEntry) -> Unit
) {
    composable<R>(
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(duration)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(duration)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(duration)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(duration)
            )
        }
    ) { backstackEntry ->
        content(backstackEntry)
    }
}


fun NavHostController.navigateToErrorDialog(resMessage: Int) {
    navigate(Screen.ErrorDialog(resMessage))
}

fun NavHostController.popUpToLogin() {
    popBackStack()
}