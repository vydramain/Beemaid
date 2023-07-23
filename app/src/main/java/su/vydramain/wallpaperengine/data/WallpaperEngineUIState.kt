package su.vydramain.wallpaperengine.data

data class WallpaperEngineUIState(
    val wallpapers: List<Wallpaper> = emptyList(),
    val error: String? = null
)
