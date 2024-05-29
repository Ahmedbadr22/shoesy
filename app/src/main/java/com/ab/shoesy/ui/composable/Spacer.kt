package com.ab.shoesy.ui.composable

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(space: Int) {
    Spacer(modifier = Modifier.height(space.dp))
}

@Composable
fun ColumnScope.VerticalSpacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}


@Composable
fun HorizontalSpacer(space: Int) {
    Spacer(modifier = Modifier.width(space.dp))
}