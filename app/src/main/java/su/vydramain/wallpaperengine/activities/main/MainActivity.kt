package su.vydramain.wallpaperengine.activities.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.app.WallpaperManager
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.activities.contracts.ActionGetContentContract

class MainActivity : AppCompatActivity() {

    private lateinit var mainSetEditText: EditText
    private lateinit var mainSetImagePreview: ImageView
    private lateinit var mainSetImageChooseButton: Button
    private lateinit var mainSetApplyWallpapersButton: Button

    private lateinit var wallpaperManager: WallpaperManager

    private val actionGetContentActivityLauncher =
        registerForActivityResult(ActionGetContentContract()) { result ->
            when {
                result !== null -> {
                    mainSetEditText.setText(result.toString())
                    mainSetImagePreview.setImageURI(result)
                    mainSetApplyWallpapersButton.isEnabled = true
                }
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wallpaperManager = WallpaperManager.getInstance(applicationContext)

        mainSetEditText = findViewById<EditText>(R.id.main_set_image_path)
        mainSetImagePreview = findViewById<ImageView>(R.id.main_set_image_preview)
        mainSetImageChooseButton = findViewById<Button>(R.id.main_set_image_choose_button)
        mainSetApplyWallpapersButton = findViewById<Button>(R.id.main_set_apply_wallpaper_button)

        mainSetImageChooseButton.setOnClickListener {
            actionGetContentActivityLauncher.launch(0)
        }

        mainSetApplyWallpapersButton.isEnabled = false
        mainSetApplyWallpapersButton.setOnClickListener {
            try {
                wallpaperManager.setBitmap(mainSetImagePreview.drawable.current.toBitmap())
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
