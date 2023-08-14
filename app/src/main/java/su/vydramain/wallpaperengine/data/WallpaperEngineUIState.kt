package su.vydramain.wallpaperengine.data

import android.util.Log
import androidx.lifecycle.MutableLiveData

data class WallpaperEngineUIState(
    val wallpapers: MutableLiveData<List<Wallpaper>> = MutableLiveData<List<Wallpaper>>(),
    var activityLauncherFunction: (Wallpaper) -> Unit = { Log.i("laun", "Pong!") },
    val loading: Boolean = false,
    val error: String? = null,
)
