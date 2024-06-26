package com.ab.shoesy.ui.screen.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.SearchButton
import com.ab.shoesy.ui.composable.TitleSection
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.screen.home.composable.BrandItem
import com.ab.shoesy.ui.theme.ShoesyTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    uiState: HomeContract.State,
    onEvent: (HomeContract.Event) -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                onClick = {},
                onCameraButtonClick = {}
            )
            TitleSection(
                modifier = Modifier.fillMaxWidth(),
                headerModifier = Modifier.padding(horizontal = 24.dp),
                title = stringResource(R.string.brands),
                onSeeAllClick = {}
            ) {
                LazyRow(
                    contentPadding = PaddingValues(start = 24.dp)
                ) {
                    items(uiState.brands) { brand ->
                        BrandItem {}
                    }
                }
            }
            TitleSection(
                modifier = Modifier.fillMaxWidth(),
                headerModifier = Modifier.padding(horizontal = 24.dp),
                title = stringResource(R.string.special_for_you),
                onSeeAllClick = {}
            ) {
                LazyRow(
                    contentPadding = PaddingValues(start = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(3) {
                        Column(
                            modifier = Modifier
                                .width(150.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(150.dp),
                                contentAlignment = Alignment.TopStart
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(10))
                                        .background(
                                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                                alpha = 0.2f
                                            )
                                        )
                                        .padding(8.dp),
                                    painter = painterResource(id = R.drawable.nike_shoe),
                                    contentDescription = null
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.nike),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                                    )
                                    FilledIconButton(
                                        modifier = Modifier.size(24.dp),
                                        onClick = { /*TODO*/ },
                                        colors = IconButtonDefaults.filledIconButtonColors(
                                            containerColor = MaterialTheme.colorScheme.background
                                        )
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(16.dp),
                                            painter = painterResource(id = R.drawable.favorite),
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                            VerticalSpacer(space = 8)
                            Text(
                                text = "Nike 1 Retro High Tie Dye",
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
                                    text = "4.5",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Text(
                                    text = "(1045 Reviews)",
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            Text(
                                text = "$235.00",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenLightPreview() {
    ShoesyTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            uiState = HomeContract.State(),
            onEvent = {},
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenDarkPreview() {
    ShoesyTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
            uiState = HomeContract.State(),
            onEvent = {},
        )
    }
}