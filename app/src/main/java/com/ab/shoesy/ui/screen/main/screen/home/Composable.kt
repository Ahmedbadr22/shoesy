package com.ab.shoesy.ui.screen.main.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    fullname: String,
    imageUrl: String,
    actions: @Composable RowScope.() -> Unit,
    onProfileIconClick: () -> Unit,
) {

    val context = LocalContext.current
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
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = fullname
                ) {
                    when (painter.state) {
                        is AsyncImagePainter.State.Error -> {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                OutlinedCard(
                                    modifier = Modifier.fillMaxSize(),
                                    shape = CircleShape
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = if(fullname.isNotEmpty()) fullname[0].toString()
                                            else fullname,
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
            }
        },
        title = {
            if (fullname.isNotEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    text = "Hello, ${fullname.substring(0, fullname.lastIndexOf(" "))}  \uD83D\uDC4B\uD83C\uDFFB",
                    overflow = TextOverflow.Ellipsis,
                )
            } else {
                Text(
                    text = "Hello, $fullname  \uD83D\uDC4B\uD83C\uDFFB",
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
        actions = actions
    )
}