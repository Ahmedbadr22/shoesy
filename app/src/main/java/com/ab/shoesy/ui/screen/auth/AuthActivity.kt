package com.ab.shoesy.ui.screen.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ab.shoesy.MainActivity
import com.ab.shoesy.ui.screen.auth.nav.AuthNavHost
import com.ab.shoesy.ui.theme.ShoesyTheme

class AuthActivity : ComponentActivity() {

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

            ShoesyTheme {
                AuthNavHost(
                    navHostController = navHostController,
                    onNavigateToMainActivity = ::navigateToMainActivity
                )
            }
        }
    }


    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}