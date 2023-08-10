package su.vydramain.wallpaperengine.ui

import android.app.WallpaperManager
import android.os.Bundle

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

    //  Opens WallpaperDetailActivity when RecyclerView item is clicked.
    private fun launchActivity(wallpaper: Wallpaper) {
        actionGetContentActivityLauncher.launch(wallpaper)
    }

    override fun onCreate(savedInstancesState: Bundle?) {
        super.onCreate(savedInstancesState)

        viewModel.registerActivityLauncherFunction(::launchActivity)

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