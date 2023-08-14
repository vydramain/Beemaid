package su.vydramain.wallpaperengine.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.MutableLiveData
import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.data.WallpaperEngineUIState
import su.vydramain.wallpaperengine.ui.components.WallpaperPreViewScreen

@Composable
fun WallpaperEngineApp(
    wallpaperEngineUIState: WallpaperEngineUIState,
    addWallpaperTemplate: () -> Unit,
) {
    WallpaperEngineAppContent(
        wallpaperEngineUIState = wallpaperEngineUIState,
        addWallpaperTemplate = addWallpaperTemplate
    )
}

@Composable
fun WallpaperEngineAppContent(
    wallpaperEngineUIState: WallpaperEngineUIState,
    addWallpaperTemplate: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            WallpaperPreViewScreen(
                wallpaperEngineUIState = wallpaperEngineUIState,
                modifier = modifier
            )
        }

        Button(
            onClick = { addWallpaperTemplate() },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .zIndex(2F),
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
        wallpaperEngineUIState = WallpaperEngineUIState(
            MutableLiveData(listOf(
                Wallpaper(),
                Wallpaper(),
                Wallpaper(),
                Wallpaper(),
                Wallpaper()
            )
        )
        ),
        addWallpaperTemplate = {}
    )
}