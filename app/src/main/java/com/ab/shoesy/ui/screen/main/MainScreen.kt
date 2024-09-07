package com.ab.shoesy.ui.screen.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ab.shoesy.ui.screen.main.navigation.MainBottomTabs
import com.ab.shoesy.ui.screen.main.navigation.MainNavHost
import com.ab.shoesy.ui.theme.ShoesyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainContract.State,
    mainScreenNavController: NavHostController
) {

    var lastTab: MainBottomTabs by remember {
        mutableStateOf(MainBottomTabs.Home)
    }
    val navBackStackEntry by mainScreenNavController.currentBackStackEntryAsState()
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
                            contentDescription = MainBottomTabs.Home.route
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Home.route } == true,
                    onClick = {
                        mainScreenNavController.navigate(MainBottomTabs.Home.route) {
                            popUpTo(lastTab.route) {
                                inclusive = true
                            }
                            lastTab = MainBottomTabs.Home
                        }
                    },
                )
                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                               androidx.compose.animation.AnimatedVisibility(
                                   visible = uiState.favoriteItemsCount > 0,
                                   enter = fadeIn(),
                                   exit = fadeOut()
                               ) {
                                   Badge {
                                       Text(uiState.favoriteItemsCount.toString(),)
                                   }
                               }
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(28.dp),
                                painter = painterResource(id = MainBottomTabs.Favorite.iconRes),
                                contentDescription = MainBottomTabs.Favorite.route
                            )
                        }
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Favorite.route } == true,
                    onClick = {
                        mainScreenNavController.navigate(MainBottomTabs.Favorite.route) {
                            popUpTo(lastTab.route) {
                                inclusive = true
                            }
                            lastTab = MainBottomTabs.Favorite
                        }
                    },
                )
                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = uiState.cartItemsCount > 0,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    Badge {
                                        Text(
                                            uiState.cartItemsCount.toString(),
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(28.dp),
                                painter = painterResource(id = MainBottomTabs.Cart.iconRes),
                                contentDescription = MainBottomTabs.Cart.route
                            )
                        }
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Cart.route } == true,
                    onClick = {
                        mainScreenNavController.navigate(MainBottomTabs.Cart.route) {
                            popUpTo(lastTab.route) {
                                inclusive = true
                            }
                            lastTab = MainBottomTabs.Cart
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = MainBottomTabs.Account.iconRes),
                            contentDescription = MainBottomTabs.Account.route
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == MainBottomTabs.Account.route } == true,
                    onClick = {}
                )
            }
        }
    ) { paddingValues ->
        MainNavHost(
            navHostController = mainScreenNavController,
            paddingValues = paddingValues,
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ShoesyTheme {
        MainScreen(
            uiState = MainContract.State(),
            mainScreenNavController = rememberNavController()
        )
    }
}