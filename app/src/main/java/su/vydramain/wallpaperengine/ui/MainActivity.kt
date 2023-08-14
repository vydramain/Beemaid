package su.vydramain.wallpaperengine.ui

import android.app.WallpaperManager
import android.os.Bundle
import android.util.Log

import androidx.activity.viewModels
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import su.vydramain.wallpaperengine.contracts.ActionGetContentAsWallpaperContract
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.ui.theme.WallpaperEngineAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var wallpaperManager: WallpaperManager

    private val viewModel: WallpaperEngineViewModel by viewModels()

    private val actionGetContentActivityLauncher =
        registerForActivityResult(ActionGetContentAsWallpaperContract()) { result ->
            when {
                result !== null -> {
                    result.duration = 0
                    result.transition = 0
                    viewModel.updateWallpaperInstance(result)
                }
            }
        }

    override fun onCreate(savedInstancesState: Bundle?) {
        super.onCreate(savedInstancesState)

        //  Opens WallpaperDetailActivity when RecyclerView item is clicked.
        viewModel.registerActivityLauncherFunction { wallpaper ->
            actionGetContentActivityLauncher.launch(
                wallpaper
            )
        }

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            WallpaperEngineAppTheme {
                WallpaperEngineApp(
                    wallpaperEngineUIState = uiState,
                    addWallpaperTemplate = {
                        viewModel.addWallpaperTemplate()
                    },
                )
            }
        }
    }
}