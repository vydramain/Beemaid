package su.vydramain.wallpaperengine.activities.main

import android.app.WallpaperManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.activities.wallpaperpreview.WallpaperPreview

class MainActivity : AppCompatActivity() {

    private lateinit var mainSetApplyWallpapersButton: Button
    private lateinit var mainSetWallpapersPreviewList: ListView
    private lateinit var mainSetAddWallpaperPreviewButton: Button

    private lateinit var wallpaperPreview: WallpaperPreview

    private lateinit var wallpaperManager: WallpaperManager

//    private val actionGetContentActivityLauncher =
//        registerForActivityResult(ActionGetContentContract()) { result ->
//            when {
//                result !== null -> {
//                    mainSetEditText.setText(result.toString())
//                    mainSetImagePreview.setImageURI(result)
//                    mainSetApplyWallpapersButton.isEnabled = true
//                }
//            }
//
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wallpaperManager = WallpaperManager.getInstance(applicationContext)

        mainSetApplyWallpapersButton = findViewById(R.id.main_set_apply_wallpaper_button)
        mainSetWallpapersPreviewList = findViewById(R.id.main_set_wallpapers_preview_list)
        mainSetAddWallpaperPreviewButton = findViewById(R.id.main_set_add_wallpaper_preview_button)

        wallpaperPreview = WallpaperPreview(
            findViewById(R.id.wallpaper_preview_path),
            findViewById(R.id.wallpaper_preview_image),
            findViewById(R.id.wallpaper_preview_choose_image_button)
        )

//        mainSetImageChooseButton.setOnClickListener {
//            actionGetContentActivityLauncher.launch(0)
//        }

//        mainSetApplyWallpapersButton.isEnabled = false
//        mainSetApplyWallpapersButton.setOnClickListener {
//            try {
//                wallpaperManager.setBitmap(mainSetImagePreview.drawable.current.toBitmap())
//            } catch (e: Exception) {
//                Toast.makeText(
//                    applicationContext,
//                    R.string.toast_cant_set_wallpaper_message,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }

        // Создаём пустой массив для хранения имен котов
        val previews: ArrayList<WallpaperPreview> = ArrayList()

        val previewsAdapter: ArrayAdapter<WallpaperPreview> =
            ArrayAdapter<WallpaperPreview>(
                this,
                R.layout.wallpaper_preview_list_item,
                previews
            )

        mainSetWallpapersPreviewList.adapter = previewsAdapter

        mainSetAddWallpaperPreviewButton.setOnKeyListener(object : OnKeyListener() {
            fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.getAction() === KeyEvent.ACTION_DOWN) if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    previews.add(0, editText.getText().toString())
                    previewsAdapter.notifyDataSetChanged()
                    editText.setText("")
                    return true
                }
                return false
            }
        })
    }
}
