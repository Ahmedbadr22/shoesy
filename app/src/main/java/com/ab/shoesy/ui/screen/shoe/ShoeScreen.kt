package com.ab.shoesy.ui.screen.shoe

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.ab.core.base.ViewSideEffect
import com.ab.domain.model.data.getAverageRate
import com.ab.domain.model.data.getReviewCount
import com.ab.domain.model.data.getTotalPrice
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.FavoriteButton
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.TitleSection
import com.ab.shoesy.ui.composable.TopBar
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.theme.ShoesyTheme
import com.ab.shoesy.ui.theme.grayColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DefaultLocale")
@Composable
fun ShoeScreen(
    uiState: ShoeContract.State,
    sideEffects: Flow<ViewSideEffect>,
    onEvent: (ShoeContract.Event) -> Unit
) {
    val context = LocalContext.current
    val navHostController = LocalNavController.current

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var showAddToCartBottomSheet by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        sideEffects.collectLatest { effect ->
            when (effect) {
                ShoeContract.SideEffects.NotValidOrderData -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.select_color_size_quantity_please),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                ShoeContract.SideEffects.SuccessCartOrderItem -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.item_added_to_cart_successfully),
                        Toast.LENGTH_SHORT
                    ).show()

                    sheetState.hide()
                    showAddToCartBottomSheet = false
                }

                ShoeContract.SideEffects.FailedCartOrderItem -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.fail_to_add_to_cart),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                onBackArrowPress = dropUnlessResumed(block = navHostController::popBackStack),
                actions = {
                    Box(
                        modifier = Modifier.wrapContentSize(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bag),
                                contentDescription = stringResource(R.string.cart)
                            )
                        }
                        Badge {
                            Text(text = uiState.cartItemsCount.toString())
                        }
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 24.dp)
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (uiState.cartItem == null) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = stringResource(R.string.price),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "$${uiState.shoe?.price}",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Button(
                        modifier = Modifier.height(50.dp),
                        onClick = {
                            showAddToCartBottomSheet = true
                        }
                    ) {
                        Text(text = stringResource(R.string.add_to_cart))
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = stringResource(R.string.total),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "$${uiState.cartItem.getTotalPrice()}",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
                    ) {
                        if (uiState.cartItem.quantity == 1) {
                            OutlinedButton(
                                onClick = {
                                    onEvent(ShoeContract.Event.DeleteCartItem)
                                },
                                shape = RoundedCornerShape(topStart = 50f, bottomStart = 50f)
                            ) {
                                Icon(
                                    Icons.Outlined.Delete,
                                    contentDescription = null
                                )
                            }
                        } else {
                            OutlinedButton(
                                onClick = {
                                    onEvent(ShoeContract.Event.DecreaseCartItem)
                                },
                                shape = RoundedCornerShape(topStart = 50f, bottomStart = 50f)
                            ) {
                                Text(text = "-")
                            }
                        }
                        Text(
                            text = "(${uiState.cartItem.quantity})",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        OutlinedButton(
                            onClick = {
                                onEvent(ShoeContract.Event.IncreaseCartItem)
                            },
                            shape = RoundedCornerShape(topEnd = 50f, bottomEnd = 50f)
                        ) {
                            Text(text = "+")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->

        if (showAddToCartBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier
                    .padding(paddingValues),
                sheetState = sheetState,
                onDismissRequest = { showAddToCartBottomSheet = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 8.dp
                    )
                ) {

                    TitleSection(
                        title = if (uiState.selectedColor != null) stringResource(
                            R.string.color_value,
                            uiState.selectedColor.name
                        )
                        else stringResource(id = R.string.color),
                        headerModifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
                            contentPadding = PaddingValues(start = 24.dp)
                        ) {
                            items(uiState.shoe?.colors ?: emptyList()) { color ->
                                ColorItem(
                                    size = 40,
                                    color = color,
                                    onClick = { onEvent(ShoeContract.Event.SelectShoeColor(color)) },
                                    isSelected = uiState.selectedColor?.id == color.id
                                )
                            }
                        }
                    }
                    TitleSection(
                        title = if (uiState.selectedSize != 0) stringResource(
                            R.string.size_value,
                            uiState.selectedSize.toString()
                        )
                        else stringResource(id = R.string.size),
                        headerModifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
                            contentPadding = PaddingValues(start = 24.dp)
                        ) {
                            items(uiState.shoe?.sizes ?: emptyList()) { value ->
                                SizeItem(
                                    size = value,
                                    onClick = { size ->
                                        onEvent(ShoeContract.Event.SelectShoeSize(size))
                                    }
                                )
                            }
                        }
                    }
                    TitleSection(
                        title = if (uiState.quantity != 0) stringResource(
                            R.string.quantity_value,
                            uiState.quantity.toString()
                        )
                        else stringResource(id = R.string.quantity),
                        headerModifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
                            contentPadding = PaddingValues(start = 24.dp)
                        ) {
                            items(uiState.shoe?.quantity ?: 0) { value ->
                                QuantityItem(
                                    num = value.inc(),
                                    onClick = { selectedQuantity ->
                                        onEvent(
                                            ShoeContract.Event.OnSelectShoeQuantity(selectedQuantity)
                                        )
                                    }
                                )
                            }
                        }
                    }
                    VerticalSpacer(space = 8)
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 24.dp),
                        onClick = {
                            onEvent(ShoeContract.Event.OnAddToCartClick)
                        }
                    ) {
                        Text(text = "Add to cart '${uiState.calculateTotalCost()}$' ")
                    }
                }
            }
        }

        if (uiState.shoe != null) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(uiState.shoe.image)
                            .decoderFactory(SvgDecoder.Factory())
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8))
                            .background(color = grayColor)
                            .padding(24.dp),
                        contentDescription = uiState.shoe.name
                    )
                    FavoriteButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopEnd),
                        onClick = {
                            uiState.shoe.let { shoe ->
                                if (shoe.isFavorite) onEvent(
                                    ShoeContract.Event.MarkShoeAsUnFavorite(
                                        shoe.id
                                    )
                                )
                                else onEvent(ShoeContract.Event.MarkShoeAsFavorite(shoe.id))
                            }
                        },
                        iconSize = 24,
                        isFavorite = uiState.shoe.isFavorite
                    )
                    LazyRow(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomEnd)
                            .clip(RoundedCornerShape(50))
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 6.dp)
                    ) {
                        items(uiState.shoe.colors) { color ->
                            ColorItem(color = color)
                        }
                    }
                }
                VerticalSpacer(space = 16)
                Text(
                    text = uiState.shoe.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                VerticalSpacer(space = 8)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(uiState.shoe.brand.image)
                            .decoderFactory(SvgDecoder.Factory())
                            .crossfade(true)
                            .build(),

                        modifier = Modifier
                            .size(32.dp),
                        contentDescription = uiState.shoe.brand.name
                    ) {
                        when (painter.state) {
                            is AsyncImagePainter.State.Error -> {
                            }

                            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                            else -> Image(
                                painter = painter,
                                contentDescription = contentDescription,
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.onBackground.copy(
                                        alpha = 0.5f
                                    )
                                )
                            )
                        }
                    }
                    Text(
                        text = uiState.shoe.brand.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = stringResource(R.string.star)
                    )
                    Text(
                        text = String.format("%.2f", uiState.shoe.getAverageRate()),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "(${uiState.shoe.getReviewCount()} Reviews)",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                VerticalSpacer(space = 8)
                Text(
                    text = stringResource(R.string.size),
                    style = MaterialTheme.typography.titleMedium
                )
                VerticalSpacer(space = 8)
                LazyRow(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(space = 6.dp)
                ) {
                    items(uiState.shoe.sizes) { size ->
                        SizeItem(size = size)
                    }
                }
                VerticalSpacer(space = 16)
                Text(
                    text = stringResource(R.string.description),
                    style = MaterialTheme.typography.titleMedium
                )
                VerticalSpacer(space = 8)
                Text(
                    text = uiState.shoe.description,
                    style = MaterialTheme.typography.labelMedium
                )
                VerticalSpacer(space = 16)
                Text(
                    text = stringResource(R.string.reviews, uiState.shoe.getReviewCount()),
                    style = MaterialTheme.typography.titleMedium
                )
                if (uiState.shoe.reviews.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((uiState.shoe.reviews.size * 108).dp),
                        contentPadding = PaddingValues(vertical = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.shoe.reviews) { review ->
                            ReviewItem(review = review)
                        }
                    }
                }

            }
        }
    }
}


@Preview
@Composable
private fun ShoeScreenLightPreview() {
    ShoesyTheme {
        ShoeScreen(
            uiState = ShoeContract.State(),
            sideEffects = emptyFlow(),
            onEvent = {}
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShoeScreenDarkPreview() {
    ShoesyTheme {
        ShoeScreen(
            uiState = ShoeContract.State(),
            sideEffects = emptyFlow(),
            onEvent = {}
        )
    }
}