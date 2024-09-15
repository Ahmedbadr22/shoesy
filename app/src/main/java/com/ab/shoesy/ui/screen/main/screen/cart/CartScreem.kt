package com.ab.shoesy.ui.screen.main.screen.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
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

            OutlinedButton(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.checkout),
                    style = MaterialTheme.typography.titleLarge
                )
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


