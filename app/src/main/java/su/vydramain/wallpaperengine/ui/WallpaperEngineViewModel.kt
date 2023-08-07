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

    fun addWallpaperTemplate() {
        val currentWallpapers = uiState.value.wallpapers
        val updatedWallpapers = currentWallpapers.toMutableList()
        updatedWallpapers.add(updatedWallpapers.size, Wallpaper())
        _uiState.value = _uiState.value.copy(
            wallpapers = updatedWallpapers,

            )
    }
}