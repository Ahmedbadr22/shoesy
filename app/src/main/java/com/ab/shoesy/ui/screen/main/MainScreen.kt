package com.ab.shoesy.ui.screen.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ab.shoesy.ui.screen.main.navigation.MainBottomTabs
import com.ab.shoesy.ui.screen.main.navigation.MainNavHost
import com.ab.shoesy.ui.theme.ShoesyTheme

@Composable
fun MainScreen() {

    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = MainBottomTabs.Home.iconRes),
                            contentDescription = null
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Home.route } == true,
                    onClick = {
                        navHostController.navigate(MainBottomTabs.Home.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    },
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = MainBottomTabs.Favorite.iconRes),
                            contentDescription = null
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Favorite.route } == true,
                    onClick = {
                        navHostController.navigate(MainBottomTabs.Favorite.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    },
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = MainBottomTabs.Cart.iconRes),
                            contentDescription = null
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Cart.route } == true,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = MainBottomTabs.Account.iconRes),
                            contentDescription = null
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Account.route } == true,
                    onClick = {}
                )
            }
        }
    ) { paddingValues ->
        MainNavHost(
            navHostController = navHostController,
            paddingValues = paddingValues,
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ShoesyTheme {
        MainScreen()
    }
}