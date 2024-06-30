package com.ab.shoesy.ui.screen.home

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.SearchButton
import com.ab.shoesy.ui.composable.TitleSection
import com.ab.shoesy.ui.screen.home.composable.BrandItem
import com.ab.shoesy.ui.screen.home.composable.StockHorizontalShoeItem
import com.ab.shoesy.ui.screen.home.composable.ShoeItem
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
                .padding(top = 16.dp)
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
                onSeeAllClick = {}
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    items(uiState.brands) { brand ->
                        BrandItem(
                            brand = brand,
                            onClick = {}
                        )
                    }
                }
            }
            TitleSection(
                modifier = Modifier.fillMaxWidth(),
                headerModifier = Modifier.padding(horizontal = 24.dp),
                title = stringResource(R.string.special_for_you),
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(uiState.specialForYouShoes) { stockItem ->
                        ShoeItem(shoe = stockItem)
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
                    items(uiState.specialForYouShoes) { item ->
                        StockHorizontalShoeItem(shoe = item)
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