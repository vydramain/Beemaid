package su.vydramain.wallpaperengine.ui

import android.os.Bundle

import androidx.activity.viewModels
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import su.vydramain.wallpaperengine.ui.theme.WallpaperEngineAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: WallpaperEngineViewModel by viewModels()

    override fun onCreate(savedInstancesState: Bundle?) {
        super.onCreate(savedInstancesState)

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            WallpaperEngineAppTheme {
                WallpaperEngineApp(
                    wallpaperEngineUIState = uiState,
                )
            }
        }
    }
}