package com.ab.shoesy.ui.screen.main.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ab.shoesy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    fullname: String? = null,
    actions: @Composable RowScope.() -> Unit,
    onProfileIconClick: () -> Unit,
) {
    MediumTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(45.dp),
                onClick = onProfileIconClick
            ) {
                Image(
                    painter = painterResource(id = R.drawable.male_avatar),
                    contentDescription = null
                )
            }
        },
        title = {
            if (fullname != null) {
                Text(text = "Hello, $fullname  \uD83D\uDC4B\uD83C\uDFFB")
            }
        },
        actions = actions
    )
}