package su.vydramain.wallpaperengine.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import su.vydramain.wallpaperengine.data.WallpaperEngineUIState

@Composable
fun WallpaperPreViewScreen(
    wallpaperEngineUIState: WallpaperEngineUIState,
    modifier: Modifier = Modifier
) {

    val wallpaperLazyListState = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        WallpaperPreViewListContent(
            wallpaperEngineUIState = wallpaperEngineUIState,
            wallpaperLazyListState = wallpaperLazyListState,
            modifier = modifier.fillMaxSize(),
        )
    }
}