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
        _uiState.value = WallpaperEngineUIState()
    }

    fun registerActivityLauncherFunction(f: (Wallpaper) -> Unit) {
        _uiState.value.activityLauncherFunction = f
    }

    fun addWallpaperTemplate(wallpaper: Wallpaper) {
        val currentList = _uiState.value.wallpapers.value
        if (null != currentList) {
            val updatedList = currentList.toMutableList()
            updatedList.add(updatedList.size, wallpaper)
            _uiState.value.wallpapers.postValue(updatedList)
        }
    }

    fun updateWallpaperInstance(wallpaper: Wallpaper) {
        val currentList = _uiState.value.wallpapers.value
        if (null != currentList) {
            _uiState.value.wallpapers.postValue(currentList.map { return@map if (it.id === wallpaper.id) wallpaper else it })
        }
    }
}