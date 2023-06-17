package su.vydramain.wallpaperengine.ui.components

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.Wallpaper

@SuppressLint("UseCompatLoadingForDrawables")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperPreView(
    wallpaper: Wallpaper
) {
    val standardPadding = 16.dp

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

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.wallpaper_settings_duration_title),
                        modifier = Modifier.width(70.dp)
                    )

                    TextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )

                    Row(modifier = Modifier.weight(1f)) {
                        Image(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = stringResource(R.string.wallpaper_settings_duration_button_add_description),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.weight(1f)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = stringResource(R.string.wallpaper_settings_duration_button_reduce_description),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.wallpaper_settings_transition_title),
                        modifier = Modifier.width(70.dp)
                    )

                    TextField(
                        value = "",
                        onValueChange = {},
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

@Preview()
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