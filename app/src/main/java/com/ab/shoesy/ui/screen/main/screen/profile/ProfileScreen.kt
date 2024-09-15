package com.ab.shoesy.ui.screen.main.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.shoesy.R
import com.ab.shoesy.ui.composable.LocalNavController
import com.ab.shoesy.ui.composable.TopBar
import com.ab.shoesy.ui.composable.VerticalSpacer
import com.ab.shoesy.ui.theme.ShoesyTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen() {
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
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.6f),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            VerticalSpacer(space = 16)
            Text(
                text = "Ahmed Badr Abdeldayem",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(space = 6)
            Text(
                text = "+02 1222742623",
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
        ProfileScreen()
    }
}