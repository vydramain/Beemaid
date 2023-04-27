package su.vydramain.wallpaperengine.activities.main

import android.app.WallpaperManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.adapters.WallpapersAdapter
import su.vydramain.wallpaperengine.contracts.ActionGetContentContract
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.models.WallpaperListViewModel
import su.vydramain.wallpaperengine.models.WallpaperListViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var wallpaperManager: WallpaperManager
    private lateinit var mainSetApplyWallpapersButton: Button

    private val actionGetContentActivityLauncher =
        registerForActivityResult(ActionGetContentContract()) { result ->
            when {
                result !== null -> {
//                    wallpaperPreviewEditText.setText(result.toString())
//                    wallpaperPreviewImagePreview.setImageURI(result)
                }
            }

        }

    private val wallpapersListViewModel by viewModels<WallpaperListViewModel> {
        WallpaperListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainSetApplyWallpapersButton = findViewById(R.id.main_set_apply_wallpaper_button)
        mainSetApplyWallpapersButton.setOnClickListener { applyWallpapers() }
        mainSetApplyWallpapersButton.isEnabled = false

//      Instantiates wallpapersAdapter. Both adapters are added to concatAdapter.
//      Which displays the contents sequentially.
        val wallpapersAdapter = WallpapersAdapter { wallpaper -> adapterOnClick(wallpaper) }

        val recyclerView: RecyclerView = findViewById(R.id.main_set_wallpaper_recycler_view)
        recyclerView.adapter = wallpapersAdapter

        wallpapersListViewModel.wallpapersLiveData.observe(this) {
            it?.let {
                wallpapersAdapter.submitList(it as MutableList<Wallpaper>)
            }
        }

        val addWallpaperPreviewButton: Button =
            findViewById(R.id.main_set_add_wallpaper_preview_button)
        addWallpaperPreviewButton.setOnClickListener {
            wallpapersListViewModel.insertWallpaper("")
        }
    }

    //  Opens WallpaperDetailActivity when RecyclerView item is clicked.
    private fun adapterOnClick(wallpaper: Wallpaper) {
        actionGetContentActivityLauncher.launch(0)
    }

    private fun applyWallpapers() {
        try {
//            wallpaperManager.setBitmap(wallpaperPreviewImagePreview.drawable.current.toBitmap())
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                R.string.toast_cant_set_wallpaper_message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
