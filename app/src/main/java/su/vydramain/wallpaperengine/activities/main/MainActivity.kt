package su.vydramain.wallpaperengine.activities.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.activities.contracts.ActionGetContentContract

class MainActivity : AppCompatActivity() {

    private lateinit var mainSetEditText: EditText
    private lateinit var mainSetImagePreview: ImageView

    private val actionGetContentActivityLauncher =
        registerForActivityResult(ActionGetContentContract()) { result ->
            when {
                result !== null -> {
                    mainSetEditText.setText(result.toString())
                    mainSetImagePreview.setImageURI(result)
                }
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainSetEditText = findViewById<EditText>(R.id.main_set_image_path)
        mainSetImagePreview = findViewById<ImageView>(R.id.main_set_image_preview)

        val mainSetImageChooseButton = findViewById<Button>(R.id.main_set_image_choose_button)
        mainSetImageChooseButton.setOnClickListener {
            actionGetContentActivityLauncher.launch(0)
        }
    }
}
