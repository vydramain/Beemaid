package su.vydramain.wallpaperengine.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.Wallpaper

@SuppressLint("UseCompatLoadingForDrawables")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperPreView(
    wallpaper: Wallpaper
) {
    val resources: Resources = Resources.getSystem()

    val standardPadding = 16.dp

    BoxWithConstraints {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = standardPadding, end = standardPadding, top = standardPadding)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = standardPadding)
                ) {
                    Text(
                        text = resources.getString(R.string.wallpaper_settings_title),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    TextField(
                        value = wallpaper.path,
                        onValueChange = { wallpaper.path = it },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = resources.getString(R.string.wallpaper_settings_duration_title),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .width(70.dp)
                        )

                        TextField(
                            value = wallpaper.path,
                            onValueChange = { wallpaper.path = it },
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Image(
                            painter = painterResource(R.drawable.add),
                            contentDescription = resources.getString(R.string.wallpaper_settings_duration_button_add_description),
                            modifier = Modifier.fillMaxHeight()
                        )

                        Image(
                            painter = painterResource(R.drawable.remove),
                            contentDescription = resources.getString(R.string.wallpaper_settings_duration_button_reduce_description),
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                }
            }
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