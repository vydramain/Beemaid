package su.vydramain.wallpaperengine.ui

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.data.WallpaperEngineUIState

class WallpaperEngineViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WallpaperEngineUIState())
    val uiState: StateFlow<WallpaperEngineUIState> = _uiState

    init {
        initWallpapers()
    }

    private fun initWallpapers() {
        _uiState.value = WallpaperEngineUIState(
            emptyList()
        )

    }

    fun insertWallpaper(wallpaperPath: String?) {
        if (wallpaperPath == null) {
            return
        }

//        _uiState.addWallpaper(
//            Wallpaper(
//                Random.nextLong(),
//                wallpaperPath,
//                Uri.EMPTY
//            )
//        )
    }

    fun updateExistWallpaper(wallpaper: Wallpaper) {
//        dataSource.replaceWallpaper(wallpaper)
    }

}