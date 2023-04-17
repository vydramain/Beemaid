package su.vydramain.wallpaperengine.activities.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import su.vydramain.wallpaperengine.R

class LoadMediaStoreActivityContract : ActivityResultContract<String, Byte?>() {

    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(
            context,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI::class.java
        )
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Byte? {
        return when {
            Activity.RESULT_OK != resultCode -> null
            else -> intent?.getByteExtra("Image", R.drawable.ic_launcher_background.toByte())
        }
    }


}