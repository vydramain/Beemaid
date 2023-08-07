package su.vydramain.wallpaperengine.data

import android.net.Uri
import java.util.UUID

class Wallpaper(
    val id: UUID = UUID.randomUUID(),
    var path: String = "",
    var imageUri: Uri = Uri.EMPTY,
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