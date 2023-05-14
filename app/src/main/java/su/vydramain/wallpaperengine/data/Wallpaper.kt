package su.vydramain.wallpaperengine.data

import android.net.Uri

class Wallpaper(
    val id: Long,
    var path: String,
    var imageUri: Uri,
    var time: Long? = null,
    var transition: Long? = null
)