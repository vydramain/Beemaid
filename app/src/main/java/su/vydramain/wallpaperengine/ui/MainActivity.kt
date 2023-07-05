package su.vydramain.wallpaperengine.ui

import android.content.res.Configuration
import android.os.Bundle

import androidx.activity.viewModels
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import su.vydramain.wallpaperengine.ui.theme.WallpaperEngineAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: WallpaperEngineViewModel by viewModels()

    override fun onCreate(savedInstancesState: Bundle?) {
        super.onCreate(savedInstancesState)

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            WallpaperEngineAppTheme {
                WallpaperEngineApp(
                    wallpaperEngineState = uiState
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun WallpaperEngineAppPreview() {
    WallpaperEngineAppTheme {
        WallpaperEngineApp(
            WallpaperEngineState(
                loading = false
            )
        )
    }
}