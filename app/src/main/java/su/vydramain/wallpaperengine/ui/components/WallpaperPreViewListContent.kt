package su.vydramain.wallpaperengine.ui.components

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier

import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.data.WallpaperEngineUIState

@Composable
fun WallpaperPreViewListContent(
    wallpaperEngineUIState: WallpaperEngineUIState,
    wallpaperLazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    wallpaperEngineUIState.wallpapers.value?.let {
        WallpaperPreViewList(
            wallpapers = it,
            launchActivityForAddWallpaper = wallpaperEngineUIState.activityLauncherFunction,
            wallpaperLazyListState = wallpaperLazyListState,
            modifier = modifier,
        )
    }
}

@Composable
fun WallpaperPreViewList(
    wallpapers: List<Wallpaper>,
    launchActivityForAddWallpaper: (Wallpaper) -> Unit,
    wallpaperLazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier, state = wallpaperLazyListState
    ) {
        items(items = wallpapers, key = { it.id }) { wallpaper ->
            WallpaperPreView(
                wallpaper = wallpaper,
                onChooseClick = { launchActivityForAddWallpaper(wallpaper) })
        }
    }
}