package com.ab.shoesy.ui.screen.main.screen.shoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ab.domain.model.data.Review
import com.ab.shoesy.R


@Composable
fun ReviewItem(
    modifier: Modifier = Modifier,
    review: Review
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(review.reviewer.profileImage)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape),
            contentDescription = review.reviewer.fullName
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Error -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedCard(
                            modifier = Modifier
                                .size(60.dp),
                            shape = CircleShape
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = review.reviewer.fullName[0].toString(),
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp
                                )
                            }
                        }
                    }
                }

                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                else -> Image(
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = contentDescription,
                )
            }
        }
        Column(
            modifier = Modifier.weight(6f),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp
            )
        ) {
            Text(
                text = review.reviewer.fullName,
                style = MaterialTheme.typography.titleMedium
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
                    text = "${review.rating}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = review.review,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun ColorItem(
    modifier: Modifier = Modifier,
    color: com.ab.domain.model.data.Color,
    size: Int = 25,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(size.dp)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = CircleShape
            )
            .background(
                color = Color(
                    android.graphics.Color.parseColor(
                        color.hex
                    )
                ), shape = CircleShape
            )
            .then(
                if (onClick != null) Modifier.clickable { onClick() }
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                Icons.Outlined.Check,
                contentDescription = null,
                tint = Color.Green
            )
        }
    }
}


@Composable
fun SizeItem(
    modifier: Modifier = Modifier,
    size: Int,
    onClick: ((Int) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(35.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = CircleShape
            )
            .background(color = MaterialTheme.colorScheme.background)
            .clickable {
                if (onClick != null) {
                    onClick(size)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$size")
    }
}

@Composable
fun QuantityItem(
    modifier: Modifier = Modifier,
    num: Int,
    onClick: (Int) -> Unit
) {
    val shape = RoundedCornerShape(15)

    Box(
        modifier = modifier
            .clip(shape)
            .size(40.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = shape
            )
            .background(color = MaterialTheme.colorScheme.background)
            .clickable { onClick(num) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$num")
    }
}