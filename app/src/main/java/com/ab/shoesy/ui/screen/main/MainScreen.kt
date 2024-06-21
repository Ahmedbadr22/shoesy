package com.ab.shoesy.ui.screen.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ab.shoesy.ui.screen.main.navigation.MainBottomTabs
import com.ab.shoesy.ui.screen.main.navigation.MainNavHost

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(painter = painterResource(id = MainBottomTabs.Home.iconRes), contentDescription = null) },
                    selected = false,
                    onClick = {

                    }
                )
            }
        }
    ) { paddingValues ->
        MainNavHost(
            navHostController = navHostController,
            paddingValues = paddingValues
        )
    }
}