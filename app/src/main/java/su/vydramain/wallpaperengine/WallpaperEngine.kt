package su.vydramain.wallpaperengine

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WallpaperEngine : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }
}