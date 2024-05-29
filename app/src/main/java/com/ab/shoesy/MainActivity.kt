    package com.ab.shoesy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ab.shoesy.ui.screen.splash.SplashScreen
import com.ab.shoesy.ui.theme.ShoesyTheme

    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoesyTheme {
                SplashScreen()
            }
        }
    }
}
