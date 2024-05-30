package com.ab.shoesy.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ab.shoesy.R


@Composable
fun GoogleOutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .height(50.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .background(color = Color.Gray.copy(alpha = 0.2f))
                .padding(4.dp),
            painter = painterResource(id = R.drawable.google),
            contentDescription = stringResource(R.string.google_icon)
        )
        Text(text = stringResource(R.string.google))
    }
}

@Composable
fun FacebookOutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .height(50.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .background(color = Color.Gray.copy(alpha = 0.2f))
                .padding(4.dp),
            painter = painterResource(id = R.drawable.facebook),
            contentDescription = stringResource(R.string.facebook_icon)
        )
        Text(text = stringResource(R.string.facebook))
    }
}