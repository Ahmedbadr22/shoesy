package com.ab.shoesy.ui.screen.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.ab.domain.model.data.Brand
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.theme.grayColor


@Composable
fun BrandItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    brand: Brand
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20))
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = onClick
            )
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(brand.image)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),

            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(color = grayColor)
                .padding(12.dp),
            contentScale = ContentScale.Crop,
            contentDescription = brand.name
        ) {
            when(painter.state){
                is AsyncImagePainter.State.Error -> {
                }
                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                else -> Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }
        }
        VerticalSpacer(space = 6)
        Text(
            text = brand.name,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
    }
}
