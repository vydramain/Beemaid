package su.vydramain.wallpaperengine.activities.contracts

import android.net.Uri
import android.content.Intent
import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContract

class ActionGetContentContract :
    ActivityResultContract<Int, Uri>() { // Don't know how to remove input parameter so just add some random integer value
    override fun createIntent(context: Context, input: Int?): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode == AppCompatActivity.RESULT_OK) { // Get the url of the image from data
            val selectedImageUri: Uri? = intent?.data
            if (null != selectedImageUri) { // update the preview image in the layout
                return selectedImageUri
            }
        }
        return null
    }
}