package su.vydramain.wallpaperengine.contracts

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import su.vydramain.wallpaperengine.data.Wallpaper

class ActionGetContentAsWallpaperContract :
    ActivityResultContract<Wallpaper, Wallpaper>() { // Don't know how to remove input parameter so just add some random integer value

    private lateinit var wallpaper: Wallpaper

    override fun createIntent(context: Context, input: Wallpaper): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        wallpaper = input

        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Wallpaper {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImageUri: Uri? = intent?.data // Get the url of the image from data
            if (null != selectedImageUri) { // update the preview image in the layout
                wallpaper.imageUri = selectedImageUri
                wallpaper.path = selectedImageUri.path.toString()
                return wallpaper
            }
        }

        return wallpaper
    }
}