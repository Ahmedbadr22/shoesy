package com.ab.shoesy.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onCameraButtonClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(width = 0.1.dp, color = Color.Gray.copy(0.6f)),
        shape = RoundedCornerShape(30),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = stringResource(R.string.search_icon)
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.what_are_you_looking_for),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray.copy(
                        0.7f
                    )
                )
            )
            IconButton(onClick = onCameraButtonClick) {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = stringResource(R.string.camera_icon)
                )
            }
        }
    }
}


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconSize: Int = 16
) {
    FilledIconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Icon(
            modifier = Modifier.size(iconSize.dp),
            painter = painterResource(id = R.drawable.favorite),
            contentDescription = stringResource(R.string.favorite),
        )
    }
}