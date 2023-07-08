package su.vydramain.wallpaperengine.data

import android.net.Uri

class Wallpaper(
    val id: Long,
    var path: String,
    var imageUri: Uri,
    var duration: Long = 0,
    var transition: Long = 0,
) {
    fun increaseDuration() {
        duration++
    }

    fun decreaseDuration() {
        duration--
    }

    fun increaseTransition() {
        transition++
    }

    fun decreaseTransition() {
        transition--
    }
}