package su.vydramain.wallpaperengine.data

import androidx.annotation.DrawableRes

class Wallpaper(
    val id: Long,
    val path: String,
    @DrawableRes val image: Int?
) {
}