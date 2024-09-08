package com.ab.shoesy.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.ab.domain.model.data.Shoe
import com.ab.domain.model.data.getAverageRate
import com.ab.domain.model.data.getReviewCount
import com.ab.shoesy.R
import com.ab.shoesy.ui.theme.grayColor
import com.valentinilk.shimmer.shimmer

@SuppressLint("DefaultLocale")
@Composable
fun ShoeItem(
    modifier: Modifier = Modifier,
    shoe: Shoe,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .width(160.dp)
            .clip(RoundedCornerShape(10))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current,
                onClick = onClick
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp),
            contentAlignment = Alignment.TopStart
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context)
                    .data(shoe.image)
                    .decoderFactory(SvgDecoder.Factory())
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10))
                    .background(color = grayColor)
                    .padding(8.dp),
                contentDescription = shoe.name,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Loading..")
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(shoe.brand.image)
                        .decoderFactory(SvgDecoder.Factory())
                        .crossfade(true)
                        .build(),

                    modifier = Modifier
                        .size(24.dp),
                    contentDescription = shoe.brand.name
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
                FavoriteButton(
                    modifier = Modifier.size(24.dp),
                    isFavorite = shoe.isFavorite,
                    onClick = onFavoriteClick
                )
            }
        }
        VerticalSpacer(space = 8)
        Text(
            text = shoe.name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = stringResource(R.string.star)
            )
            Text(
                text = String.format("%.2f", shoe.getAverageRate()),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "(${shoe.getReviewCount()} Reviews)",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = "$${shoe.price}",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun ShoeShimmerItem(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(160.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .shimmer()
                .clip(RoundedCornerShape(10))
                .background(color = Color.Gray)
                .background(color = grayColor)
                .padding(8.dp),
        )
        VerticalSpacer(space = 8)
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(10.dp)
                .shimmer()
                .clip(RoundedCornerShape(15))
                .background(color = Color.Gray)
        )
        Box(
            modifier = Modifier
                .width(90.dp)
                .height(10.dp)
                .shimmer()
                .clip(RoundedCornerShape(15))
                .background(color = Color.Gray)
        )
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(10.dp)
                .shimmer()
                .clip(RoundedCornerShape(15))
                .background(color = Color.Gray)
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ShoeHorizontalShoeItem(
    modifier: Modifier = Modifier,
    shoe: Shoe,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10))
            .clickable { onClick() }
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(shoe.image)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(10))
                .background(color = grayColor)
                .padding(8.dp),
            contentDescription = shoe.name
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 2.dp,
                alignment = Alignment.Top
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(shoe.brand.image)
                        .decoderFactory(SvgDecoder.Factory())
                        .crossfade(true)
                        .build(),

                    modifier = Modifier
                        .size(24.dp),
                    contentDescription = shoe.brand.name
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
                FavoriteButton(
                    modifier = Modifier.size(30.dp),
                    isFavorite = shoe.isFavorite,
                    onClick = onFavoriteClick,
                    iconSize = 24
                )
            }
            Text(
                text = shoe.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = shoe.description,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
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
                    text = String.format("%.1f", shoe.getAverageRate()),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "(${shoe.getReviewCount()} Reviews)",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = "$${shoe.price}",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}