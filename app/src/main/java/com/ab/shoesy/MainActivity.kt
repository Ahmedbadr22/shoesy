    package com.ab.shoesy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ab.shoesy.ui.navigation.AppNavHost
import com.ab.shoesy.ui.theme.ShoesyTheme

    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            ShoesyTheme {
                AppNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navHostController = navHostController
                )
            }
        }
    }
}
