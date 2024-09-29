package com.ab.shoesy.ui.screen.main.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.TopBar
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.theme.ShoesyTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    uiState: ProfileContract.State
) {
    val context = LocalContext.current
    val navHostController = LocalNavController.current

    Scaffold(
        topBar = {
            TopBar(
                title = {
                    Text(
                        text = stringResource(R.string.profile),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                onBackArrowPress = {
                    navHostController.navigateUp()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context)
                    .data(uiState.userDetail?.profileImageUrl)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(150.dp)
                ,
                contentScale = ContentScale.FillBounds,
                contentDescription = uiState.userDetail?.fullname.orEmpty()
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
                                        text = if(uiState.userDetail?.fullname != null) uiState.userDetail.fullname[0].toString()
                                        else uiState.userDetail?.fullname.orEmpty(),
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
            VerticalSpacer(space = 16)
            Text(
                text = uiState.userDetail?.fullname.orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(space = 6)
            Text(
                text = uiState.userDetail?.email.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ShoesyTheme {
        ProfileScreen(uiState = ProfileContract.State())
    }
}