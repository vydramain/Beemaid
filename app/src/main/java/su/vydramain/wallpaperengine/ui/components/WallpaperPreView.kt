package su.vydramain.wallpaperengine.ui.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.net.Uri

import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.foundation.Image
import androidx.compose.ui.text.TextRange
import androidx.compose.material.TextField
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.composed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview


import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.Wallpaper

@Composable
fun WallpaperPreView(
    wallpaper: Wallpaper
) {
    val standardPadding = 16.dp

    val durationValue = 0
    val transitionValue = 0

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.wallpaper_settings_title),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )

                RowWithParameterAndControls(
                    name = stringResource(R.string.wallpaper_settings_duration_title),
                    value = durationValue.toString(),
                    onValueChange = {},
                    onClickAddButton = {},
                    onClickReduceButton = {}
                )

                RowWithParameterAndControls(
                    name = stringResource(R.string.wallpaper_settings_transition_title),
                    value = transitionValue.toString(),
                    onValueChange = {},
                    onClickAddButton = {},
                    onClickReduceButton = {},
                )
            }

            Image(
                painter = painterResource(R.drawable.ic_menu_gallery),
                contentDescription = stringResource(R.string.wallpaper_preview_description),
                modifier = Modifier
                    .size(72.dp)
                    .weight(1f)
            )

            // Other components can be added here for additional settings
        }

        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Text(text = stringResource(R.string.wallpaper_choose_button_text))
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable()
fun WallpaperPreViewPreview() {
    WallpaperPreView(
        Wallpaper(
            1,
            "empty_path",
            Uri.EMPTY,
            0,
            0
        )
    )
}

@Composable
fun RowWithParameterAndControls(
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    onClickAddButton: () -> Unit,
    onClickReduceButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .composed { modifier }) {
        Text(
            text = name,
            modifier = Modifier.width(70.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Row(modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(R.string.wallpaper_settings_transition_button_add_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = stringResource(R.string.wallpaper_settings_transition_button_reduce_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun RowWithParameterAndControlsPreview() {
    val temporaryValue = 0

    RowWithParameterAndControls(
        name = "temporary_name",
        value = temporaryValue.toString(),
        onValueChange = {},
        onClickAddButton = {},
        onClickReduceButton = {})
}