package com.ab.shoesy.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.ab.domain.model.data.CartItem
import com.ab.domain.model.data.getTotalPrice
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.TopBar
import com.ab.shoesy.ui.theme.grayColor

@Composable
fun CartScreen(
    uiState: CartContract.State,
    onEvent: (CartContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.cart),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
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
                    onDeleteClick = { id ->
                        onEvent(CartContract.Event.DeleteCartItem(id))
                    }
                )
            }
        }
    }
}

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    cartItem: CartItem,
    onInc: (CartItem) -> Unit,
    onDec: (CartItem) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    val context = LocalContext.current


    Row(
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(10))
            .clickable { }
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(cartItem.shoeImage)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10))
                .background(color = grayColor)
                .padding(8.dp),
            contentDescription = cartItem.shoeName
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .height(92.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = cartItem.shoeName,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${cartItem.shoeBrand.name} . ${cartItem.color.name} . ${cartItem.size}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${cartItem.getTotalPrice()}",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 8.dp
                    )
                ) {
                    if (cartItem.quantity > 1) {
                        OutlinedIconButton(
                            modifier = Modifier.size(30.dp),
                            onClick = { onDec(cartItem) },
                        ) {
                            Text(text = "-")
                        }
                    } else {
                        IconButton(
                            modifier = Modifier.size(30.dp),
                            onClick = { onDeleteClick(cartItem.id) },
                        ) {
                            Icon(
                                Icons.Outlined.Delete,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                    Text(text = "${cartItem.quantity}")
                    OutlinedIconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = { onInc(cartItem) }
                    ) {
                        Text(text = "+")
                    }
                }
            }
        }
    }
}
