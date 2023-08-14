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

    fun registerActivityLauncherFunction(f: (Wallpaper) -> Unit) {
        _uiState.value.activityLauncherFunction = f
    }

    fun addWallpaperTemplate() {
        val currentWallpapers = uiState.value.wallpapers
        val updatedWallpapers = currentWallpapers.toMutableList()
        updatedWallpapers.add(updatedWallpapers.size, Wallpaper())
        _uiState.value = _uiState.value.copy(
            wallpapers = updatedWallpapers
        )
    }

    fun updateWallpaperInstance(wallpaper: Wallpaper) {
        val updatedWallpapers = uiState.value.wallpapers.toMutableList()
        _uiState.value = _uiState.value.copy(
            wallpapers = updatedWallpapers
                .map { return@map if (it.id == wallpaper.id) wallpaper else it }
                .toMutableList()
        )
    }
}