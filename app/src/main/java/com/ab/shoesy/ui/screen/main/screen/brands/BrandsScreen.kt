package com.ab.shoesy.ui.screen.main.screen.brands

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.lifecycle.compose.dropUnlessResumed
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.BrandItem
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.TopBar
import com.ab.shoesy.ui.screen.main.navigation.toRoute
import com.ab.shoesy.ui.screen.main.screen.brand.BrandContract

@Composable
fun BrandsScreen(
    uiState: BrandContract.State,
    onEvent: (BrandContract.Event) -> Unit
) {
    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.brands),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                onBackArrowPress = navHostController::navigateUp
            )
        }
    ) { paddingValues ->
        if (uiState.loading) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (uiState.brands.isNotEmpty()) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(paddingValues),
                    columns = GridCells.Fixed(4)
                ) {
                    items(uiState.brands) { brand ->
                        BrandItem(
                            onClick = dropUnlessResumed {
                                navHostController.navigate(brand.toRoute())
                            },
                            brand = brand
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 8.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(id = R.drawable.sneaker),
                        contentDescription = stringResource(R.string.sneakers)
                    )
                    Text(
                        text = stringResource(R.string.not_available_yet),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}