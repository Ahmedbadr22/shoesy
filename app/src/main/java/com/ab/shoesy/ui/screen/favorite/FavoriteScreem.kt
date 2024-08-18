package com.ab.shoesy.ui.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.ShoeHorizontalShoeItem
import com.ab.shoesy.ui.composable.TopBar
import com.ab.shoesy.ui.navigation.Screen

@Composable
fun FavoriteScreen(
    uiState: FavoriteContract.State,
    onEvent: (FavoriteContract.Event) -> Unit
) {
    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                title = {
                    Text(text = stringResource(id = R.string.favorite), style = MaterialTheme.typography.titleLarge)
                }
            )
        }
    ) { paddingValues ->
        if (uiState.shoes.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .padding(horizontal = 24.dp)
                    .padding(paddingValues)
            ) {
                items(uiState.shoes) { shoe ->
                    ShoeHorizontalShoeItem(
                        shoe = shoe,
                        onClick = {
                            navHostController.navigate(Screen.ShoeDetail(shoe.id))
                        },
                        onFavoriteClick = {
                            if (shoe.isFavorite) {
                                onEvent(FavoriteContract.Event.MarkAsUnFavoriteShoe(shoe.id))
                            }
                        }
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.broken_heart),
                    contentDescription = stringResource(
                        R.string.favorite
                    )
                )
                Text(
                    text = stringResource(R.string.found_0_favorite_shoe_s),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}