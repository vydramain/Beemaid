package su.vydramain.wallpaperengine.data

import android.util.Log

data class WallpaperEngineUIState(
    val wallpapers: List<Wallpaper> = emptyList(),
    var activityLauncherFunction: (Wallpaper) -> Unit = { Log.i("laun", "Pong!") },
    val loading: Boolean = false,
    val error: String? = null,
)
