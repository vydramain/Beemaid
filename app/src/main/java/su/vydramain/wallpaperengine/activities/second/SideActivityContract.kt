package su.vydramain.wallpaperengine.activities.second

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class SideActivityContract : ActivityResultContract<String, String?>() {

    override fun createIntent(context: Context, input: String?): Intent {
        return Intent(context, SideActivity::class.java).putExtra("side_activity_string", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return when {
            Activity.RESULT_OK != resultCode -> null
            else -> intent?.getStringExtra("side_activity_string")
        }
    }
}