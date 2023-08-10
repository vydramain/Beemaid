package su.vydramain.wallpaperengine.data

data class WallpaperEngineUIState(
    val wallpapers: List<Wallpaper> = emptyList(),
    var activityLauncherFunction: (Wallpaper) -> Unit = {},
    val loading: Boolean = false,
    val error: String? = null,
)
