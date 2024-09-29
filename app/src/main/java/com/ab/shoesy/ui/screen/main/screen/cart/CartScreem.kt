package com.ab.shoesy.ui.screen.main.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.TopBar
import com.ab.shoesy.ui.theme.ShoesyTheme

@Composable
fun CartScreen(
    uiState: CartContract.State,
    onEvent: (CartContract.Event) -> Unit
) {
    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.cart),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                onBackArrowPress = {
                    navHostController.navigateUp()
                }
            )
        },
        bottomBar = {

            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 24.dp)
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.sub_total),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "$${uiState.calculateTotal()}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.tax),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "14% ($${uiState.calculateTaxValue()})",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.total),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "$${uiState.calculateGrandTotal()}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {},
                    enabled = uiState.items.isNotEmpty()
                ) {
                    Text(
                        text = stringResource(R.string.checkout),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

        }
    ) { paddingValues ->
        if (uiState.items.isEmpty()) {
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
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                items(uiState.items) { cartItem ->
                    CartItem(
                        cartItem = cartItem,
                        onInc = {
                            onEvent(
                                CartContract.Event.IncreaseCartItem(
                                    cartItem.id,
                                    cartItem.shoeId,
                                    cartItem.quantity
                                )
                            )
                        },
                        onDec = {
                            onEvent(
                                CartContract.Event.DecreaseCartItem(
                                    cartItem.id,
                                    cartItem.shoeId,
                                    cartItem.quantity
                                )
                            )
                        },
                        onDeleteClick = {
                            onEvent(CartContract.Event.DeleteCartItem(cartItem.id))
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CartScreenPreview() {
    ShoesyTheme {
        CartScreen(uiState = CartContract.State()) {

        }
    }
}


