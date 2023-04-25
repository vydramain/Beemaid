package su.vydramain.wallpaperengine.views.WallpaperPreView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import su.vydramain.wallpaperengine.R

class WallpaperPreView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var editText: EditText
    private var imagePreview: ImageView
    private var imageChooseButton: Button

    init {
        View.inflate(context, R.layout.wallpaper_pre_view, this)

        editText = findViewById<EditText>(R.id.wallpaper_settings_path)
        imagePreview = findViewById<ImageView>(R.id.wallpaper_preview)
        imageChooseButton = findViewById<Button>(R.id.wallpaper_choose_button)
    }
}