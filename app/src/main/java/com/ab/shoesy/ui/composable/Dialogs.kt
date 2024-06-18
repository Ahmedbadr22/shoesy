package com.ab.shoesy.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ab.shoesy.R
import com.ab.shoesy.ui.theme.ShoesyTheme

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    message: Int,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = modifier.fillMaxWidth(0.9f),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                    alignment = Alignment.CenterVertically
                ),
            ) {
                Icon(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.i),
                    contentDescription = stringResource(R.string.i_icon),
                    tint = MaterialTheme.colorScheme.error
                )
                Text(text = stringResource(R.string.error), style = MaterialTheme.typography.titleLarge)
                Text(
                    text = stringResource(id = message),
                    style = MaterialTheme.typography.bodyMedium
                )
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = onDismiss
                ) {
                    Text(text = "Ok")
                }
            }
        }
    }
}


@Preview
@Composable
private fun ErrorDialogLightPreview() {
    ShoesyTheme {
        ErrorDialog(
            onDismiss = {},
            message = R.string.email
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorDialogDarkPreview() {
    ShoesyTheme {
        ErrorDialog(
            onDismiss = {},
            message = R.string.email
        )
    }
}