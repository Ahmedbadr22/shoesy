package com.ab.shoesy.ui.screen.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ab.shoesy.ui.screen.main.navigation.MainBottomTabs
import com.ab.shoesy.ui.screen.main.navigation.MainNavHost
import com.ab.shoesy.ui.theme.ShoesyTheme

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()

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
                    selected = true,
                    onClick = {},
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = MainBottomTabs.Favorite.iconRes),
                            contentDescription = null
                        )
                    },
                    selected = false,
                    onClick = {}
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

@Preview
@Composable
private fun MainScreenPreview() {
    ShoesyTheme {
        MainScreen()
    }
}