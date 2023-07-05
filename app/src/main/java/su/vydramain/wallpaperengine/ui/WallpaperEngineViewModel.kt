package su.vydramain.wallpaperengine.ui

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

data class WallpaperEngineState(
    val loading: Boolean = false
)

class WallpaperEngineViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WallpaperEngineState(loading = true))
    val uiState: StateFlow<WallpaperEngineState> = _uiState

}