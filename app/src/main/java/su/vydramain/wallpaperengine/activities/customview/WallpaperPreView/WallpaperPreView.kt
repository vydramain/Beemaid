package su.vydramain.wallpaperengine.activities.customview.WallpaperPreView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import su.vydramain.wallpaperengine.R

class WallpaperPreView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.wallpaper_pre_view, this)
    }
}