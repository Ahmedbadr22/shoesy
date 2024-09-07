    package com.ab.shoesy

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.navigation.AppNavHost
import com.ab.shoesy.ui.theme.ShoesyTheme

    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        setContent {
            val navHostController = rememberNavController()
            val mainScreenNavController = rememberNavController()

            ShoesyTheme {
                CompositionLocalProvider(LocalNavController provides navHostController) {
                    AppNavHost(
                        modifier = Modifier.fillMaxSize(),
                        navHostController = navHostController,
                        mainScreenNavController = mainScreenNavController
                    )
                }
            }
        }
    }
}
