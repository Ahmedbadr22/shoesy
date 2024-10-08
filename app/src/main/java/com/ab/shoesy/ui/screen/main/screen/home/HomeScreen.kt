package com.ab.shoesy.ui.screen.main.screen.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.BadgeButton
import com.ab.shoesy.ui.composable.BrandItem
import com.ab.shoesy.ui.composable.BrandShimmerItem
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.SearchButton
import com.ab.shoesy.ui.composable.ShoeHorizontalShoeItem
import com.ab.shoesy.ui.composable.ShoeItem
import com.ab.shoesy.ui.composable.ShoeShimmerItem
import com.ab.shoesy.ui.composable.TitleSection
import com.ab.shoesy.ui.screen.main.navigation.Screen
import com.ab.shoesy.ui.screen.main.navigation.toRoute
import com.ab.shoesy.ui.theme.ShoesyTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    uiState: HomeContract.State,
    onEvent: (HomeContract.Event) -> Unit,
) {
    val navHostController = LocalNavController.current
    Scaffold(
        topBar = {
            HomeTopBar(
                fullname = uiState.userDetail?.fullname.orEmpty(),
                imageUrl = uiState.userDetail?.profileImageUrl.orEmpty(),
                actions = {
                    BadgeButton(
                        iconPainter = painterResource(id = R.drawable.favorite),
                        onClick = {
                            navHostController.navigate(Screen.Favorites)
                        },
                        count = uiState.favoriteItemsCount
                    )
                    BadgeButton(
                        iconPainter = painterResource(id = R.drawable.bag),
                        onClick = {
                            navHostController.navigate(Screen.Cart)
                        },
                        count = uiState.cartItemsCount
                    )
                },
                onProfileIconClick = {
                    navHostController.navigate(Screen.Profile)
                }
            )
        }
    ) { innerPaddingValues ->
        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .padding(top = 8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
                onSeeAllClick = dropUnlessResumed {
                    navHostController.navigate(Screen.Brands)
                }
            ) {

                if (uiState.loading) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        items(5) {
                            BrandShimmerItem()
                        }
                    }
                } else {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        items(uiState.brands) { brand ->
                            BrandItem(
                                brand = brand,
                                onClick = {
                                    navHostController.navigate(brand.toRoute())
                                }
                            )
                        }
                    }
                }
            }
            TitleSection(
                modifier = Modifier.fillMaxWidth(),
                headerModifier = Modifier.padding(horizontal = 24.dp),
                title = stringResource(R.string.special_for_you),
            ) {
                if (!uiState.specialForYouLoading) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(uiState.specialForYouShoes) { shoe ->
                            ShoeItem(
                                shoe = shoe,
                                onClick = {
                                    navHostController.navigate(Screen.ShoeDetail(shoe.id))
                                },
                                onFavoriteClick = {
                                    if (shoe.isFavorite) onEvent(
                                        HomeContract.Event.MarkSpecialForYouShoeAsUnFavorite(
                                            shoe.id
                                        )
                                    )
                                    else onEvent(
                                        HomeContract.Event.MarkSpecialForYouShoeAsFavorite(
                                            shoe.id
                                        )
                                    )
                                }
                            )
                        }
                    }
                } else {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(2) {
                            ShoeShimmerItem()
                        }
                    }
                }
            }

            TitleSection(
                modifier = Modifier
                    .fillMaxWidth(),
                headerModifier = Modifier.padding(horizontal = 24.dp),
                title = stringResource(R.string.most_popular),
                onSeeAllClick = {}
            ) {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 420.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(uiState.specialForYouShoes) { shoe ->
                        ShoeHorizontalShoeItem(
                            shoe = shoe,
                            onFavoriteClick = {
                                if (shoe.isFavorite) onEvent(
                                    HomeContract.Event.MarkSpecialForYouShoeAsUnFavorite(
                                        shoe.id
                                    )
                                )
                                else onEvent(HomeContract.Event.MarkSpecialForYouShoeAsFavorite(shoe.id))
                            },
                            onClick = {
                                navHostController.navigate(Screen.ShoeDetail(shoe.id))
                            }
                        )
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
            uiState = HomeContract.State(),
            onEvent = {},
        )
    }
}