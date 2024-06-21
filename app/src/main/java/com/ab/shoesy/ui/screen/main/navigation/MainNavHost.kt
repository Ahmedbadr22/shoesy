package com.ab.shoesy.ui.screen.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.shoesy.ui.screen.home.HomeScreen


@Composable
fun MainNavHost(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = MainBottomTabs.Home) {
        composable<MainBottomTabs.Home> {
            HomeScreen(paddingValues= paddingValues)
        }
    }
}