package su.vydramain.wallpaperengine.views.wallpaperpreview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import su.vydramain.wallpaperengine.R

class WallpaperPreView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var pathInput: EditText
    private var imagePreview: ImageView
    private var imageChooseButton: Button
    private var durationInput: EditText
    private var transitionInput: EditText

    init {
        View.inflate(context, R.layout.wallpaper_pre_view, this)

        pathInput = findViewById(R.id.wallpaper_settings_path)
        imagePreview = findViewById(R.id.wallpaper_preview)
        imageChooseButton = findViewById(R.id.wallpaper_choose_button)
        durationInput = findViewById(R.id.wallpaper_settings_duration_input)
        transitionInput = findViewById(R.id.wallpaper_settings_transition_input)
    }
}