package su.vydramain.wallpaperengine.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable

import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.WallpaperEngineUIState

@Composable
fun WallpaperEngineApp(
    wallpaperEngineUIState: WallpaperEngineUIState,
) {
}

@Composable
fun WallpaperEngineAppContent(
    modifier: Modifier = Modifier,
    wallpaperEngineUIState: WallpaperEngineUIState,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {

        }

        Button(
            onClick = {},
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.wallpaper_engine_app_content_add_new_wallpaper),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun WallpaperEngineAppContentPreview() {
    WallpaperEngineAppContent(
        wallpaperEngineUIState = WallpaperEngineUIState()
    )
}