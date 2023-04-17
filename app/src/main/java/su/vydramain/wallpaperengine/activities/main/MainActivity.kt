package su.vydramain.wallpaperengine.activities.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import su.vydramain.wallpaperengine.R


class MainActivity : AppCompatActivity() {

    private lateinit var mainSetImagePreview: ImageView
//    lateinit var mainSetImageChooseButton

    // constant to compare
    // the activity result code
    private var SELECT_PICTURE = 200;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainSetImagePreview = findViewById<ImageView>(R.id.main_set_image_preview)

        val mainSetImageChooseButton = findViewById<Button>(R.id.main_set_image_choose_button)
        mainSetImageChooseButton.setOnClickListener {
            imageChooser()
        }
    }

    // this function is triggered when
    // the Select Image Button is clicked
    private fun imageChooser() {

        // create an instance of the
        // intent of the type image
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT

        // pass the constant to compare it
        // with the returned requestCode

        startActivityForResult(
            Intent.createChooser(i, R.string.main_set_image_title.toString()),
            SELECT_PICTURE
        )
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                val selectedImageUri: Uri? = data?.data
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    mainSetImagePreview.setImageURI(selectedImageUri)
                }
            }
        }
    }
}