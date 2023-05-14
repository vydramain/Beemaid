package su.vydramain.wallpaperengine.activities.main

import android.app.WallpaperManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.adapters.WallpapersAdapter
import su.vydramain.wallpaperengine.contracts.ActionGetContentAsWallpaperContract
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.models.WallpaperListViewModel
import su.vydramain.wallpaperengine.models.WallpaperListViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var wallpaperManager: WallpaperManager

    private lateinit var addWallpaperPreviewButton: Button
    private lateinit var mainSetApplyWallpapersButton: Button

    private lateinit var wallpapersAdapter: WallpapersAdapter
    private val wallpapersListViewModel by viewModels<WallpaperListViewModel> {
        WallpaperListViewModelFactory(this)
    }

    private var tmpWallpaper = Wallpaper(0, "", Uri.parse(""))
    private val actionGetContentActivityLauncher =
        registerForActivityResult(ActionGetContentAsWallpaperContract()) { result ->
            when {
                result !== null -> {
                    tmpWallpaper = result
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting up main button for applying new wallpapers config to daemon
        mainSetApplyWallpapersButton = findViewById(R.id.main_set_apply_wallpaper_button)
        mainSetApplyWallpapersButton.setOnClickListener { applyWallpapers() }
        mainSetApplyWallpapersButton.isEnabled = false

        // Setting up button for adding new wallpaper in wallpapers config
        addWallpaperPreviewButton = findViewById(R.id.main_set_add_wallpaper_preview_button)
        addWallpaperPreviewButton.setOnClickListener {
            wallpapersListViewModel.insertWallpaper("")
        }

        // Instantiates wallpapersAdapter which displays the contents sequentially.
        wallpapersAdapter = WallpapersAdapter { wallpaper -> adapterOnClick(wallpaper) }

        val recyclerView: RecyclerView = findViewById(R.id.main_set_wallpaper_recycler_view)
        recyclerView.adapter = wallpapersAdapter

        wallpapersListViewModel.wallpapersLiveData.observe(this) {
            it?.let {
                wallpapersAdapter.submitList(it as MutableList<Wallpaper>)
            }
        }
    }

    //  Opens WallpaperDetailActivity when RecyclerView item is clicked.
    private fun adapterOnClick(wallpaper: Wallpaper) {
        actionGetContentActivityLauncher.launch(wallpaper)
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
