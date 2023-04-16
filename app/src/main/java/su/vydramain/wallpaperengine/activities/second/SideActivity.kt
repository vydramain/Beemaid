package su.vydramain.wallpaperengine.activities.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import su.vydramain.wallpaperengine.activities.main.MainActivity
import su.vydramain.wallpaperengine.R

class SideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side)

        val text = intent.getStringExtra("side_activity_string")
        val editText = findViewById<EditText>(R.id.activity_side_et_input_text);
        editText.setText(text)

        findViewById<Button>(R.id.activity_side_et_submit_button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("side_activity_string", editText.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}