package su.vydramain.wallpaperengine.activities.main

import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.app.WallpaperManager

import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.contracts.ActionGetContentContract
import su.vydramain.wallpaperengine.views.WallpaperPreView.WallpaperPreView

class MainActivity : AppCompatActivity() {

    private lateinit var wallpaperPreviewEditText: EditText
    private lateinit var wallpaperPreviewImagePreview: ImageView
    private lateinit var wallpaperPreviewImageChooseButton: Button

    private lateinit var mainSetApplyWallpapersButton: Button
    private lateinit var mainSetWallpaperPreView: WallpaperPreView

    private lateinit var wallpaperManager: WallpaperManager

    private val actionGetContentActivityLauncher =
        registerForActivityResult(ActionGetContentContract()) { result ->
            when {
                result !== null -> {
                    wallpaperPreviewEditText.setText(result.toString())
                    wallpaperPreviewImagePreview.setImageURI(result)
                    mainSetApplyWallpapersButton.isEnabled = true
                }
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wallpaperManager = WallpaperManager.getInstance(applicationContext)

        mainSetWallpaperPreView = findViewById(R.id.main_set_wallpaper_preview)

        wallpaperPreviewEditText =
            mainSetWallpaperPreView.findViewById(R.id.wallpaper_settings_path)
        wallpaperPreviewImagePreview = mainSetWallpaperPreView.findViewById(R.id.wallpaper_preview)
        wallpaperPreviewImageChooseButton =
            mainSetWallpaperPreView.findViewById(R.id.wallpaper_choose_button)

        mainSetApplyWallpapersButton = findViewById(R.id.main_set_apply_wallpaper_button)

        wallpaperPreviewImageChooseButton.setOnClickListener {
            actionGetContentActivityLauncher.launch(0)
        }

        mainSetApplyWallpapersButton.isEnabled = false
        mainSetApplyWallpapersButton.setOnClickListener {
            try {
                wallpaperManager.setBitmap(wallpaperPreviewImagePreview.drawable.current.toBitmap())
            } catch (e: Exception) {
                Toast.makeText(
                    applicationContext,
                    R.string.toast_cant_set_wallpaper_message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
