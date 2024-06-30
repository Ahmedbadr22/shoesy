package com.ab.shoesy.ui.screen.brand

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.ab.domain.model.data.Brand
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.ShoeItem
import com.ab.shoesy.ui.theme.ShoesyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandScreen(
    brand: Brand,
    uiState: BrandContract.State,
    onEvent: (BrandContract.Event) -> Unit
) {
    val context = LocalContext.current
    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(
                            vertical = 4.dp,
                            horizontal = 8.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(brand.image)
                                .decoderFactory(SvgDecoder.Factory())
                                .crossfade(true)
                                .build(),

                            modifier = Modifier
                                .size(40.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = brand.name
                        ) {
                            when (painter.state) {
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
                        Text(
                            text = brand.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = dropUnlessResumed {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Outlined.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        

        if (uiState.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (uiState.shoes.isNotEmpty()) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(paddingValues),
                    columns = GridCells.Fixed(2)
                ) {
                    items(uiState.shoes) { shoe ->
                        ShoeItem(shoe = shoe, onClick = {})
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
                        painter = painterResource(id = R.drawable.sneaker),
                        contentDescription = stringResource(
                            R.string.sneakers
                        )
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

@Preview
@Composable
private fun BrandScreenLightPreview() {
    ShoesyTheme {
        BrandScreen(
            brand = Brand(
                id = 0,
                name = "",
                image = "",
                stockItemCount = 0
            ),
            uiState = BrandContract.State(),
            onEvent = { }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BrandScreenDarkPreview() {
    ShoesyTheme {
        BrandScreen(
            brand = Brand(
                id = 0,
                name = "Nike",
                image = "",
                stockItemCount = 0
            ),
            uiState = BrandContract.State(),
            onEvent = { }
        )
    }
}