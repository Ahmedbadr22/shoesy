package com.ab.shoesy.ui.composable

import androidx.compose.runtime.Composable

@Composable
fun ScreenContent(content: @Composable () -> Unit) {

    content()
}