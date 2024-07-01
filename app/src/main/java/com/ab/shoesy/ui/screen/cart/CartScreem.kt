package com.ab.shoesy.ui.screen.cart

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen() {
    Scaffold(
        topBar = {
            TopBar(
                title = {
                    Text(text = stringResource(id = R.string.cart), style = MaterialTheme.typography.titleLarge)
                }
            )
        }
    ) {

    }
}