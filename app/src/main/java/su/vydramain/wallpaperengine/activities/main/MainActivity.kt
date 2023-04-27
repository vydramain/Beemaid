package su.vydramain.wallpaperengine.activities.main

import android.app.WallpaperManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.adapters.WallpapersAdapter
import su.vydramain.wallpaperengine.contracts.ActionGetContentContract
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.models.WallpaperListViewModel
import su.vydramain.wallpaperengine.models.WallpaperListViewModelFactory

const val WALLPAPER_ID = "wallpaper id"

class MainActivity : AppCompatActivity() {
    private val wallpapersListViewModel by viewModels<WallpaperListViewModel> {
        WallpaperListViewModelFactory(this)
    }

    private lateinit var mainSetApplyWallpapersButton: Button

    private lateinit var wallpaperManager: WallpaperManager

    private val actionGetContentActivityLauncher =
        registerForActivityResult(ActionGetContentContract()) { result ->
            when {
                result !== null -> {
//                    wallpaperPreviewEditText.setText(result.toString())
//                    wallpaperPreviewImagePreview.setImageURI(result)
                    mainSetApplyWallpapersButton.isEnabled = true
                }
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and flowersAdapter. Both adapters are added to concatAdapter.
which displays the contents sequentially */
        val wallpapersAdapter = WallpapersAdapter { flower -> adapterOnClick(flower) }
        val concatAdapter = ConcatAdapter(wallpapersAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.main_set_wallpaper_preview)
        recyclerView.adapter = concatAdapter

        val wallpapersLiveData = wallpapersListViewModel.wallpapersLiveData
        wallpapersLiveData.observe(this) {
            val it = this
            it.let {
                wallpapersAdapter.submitList(it as MutableList<Wallpaper>)
            }
        }

//        wallpapersListViewModel.flowersLiveData.observe(this) {
//            it?.let {
//                wallpapersAdapter.submitList(it as MutableList<Wallpaper>)
//                        headerAdapter.updateFlowerCount(it.size)
//            }
//        }

        val fab: Button = findViewById(R.id.main_set_add_wallpaper_preview_button)
        fab.setOnClickListener {
            fabOnClick()
        }


//        wallpaperManager = WallpaperManager.getInstance(applicationContext)

//        mainSetApplyWallpapersButton = findViewById(R.id.main_set_apply_wallpaper_button)

//        wallpaperPreviewImageChooseButton.setOnClickListener {
//            actionGetContentActivityLauncher.launch(0)
//        }

//        mainSetApplyWallpapersButton.isEnabled = false
//        mainSetApplyWallpapersButton.setOnClickListener {
//            try {
//                wallpaperManager.setBitmap(wallpaperPreviewImagePreview.drawable.current.toBitmap())
//            } catch (e: Exception) {
//                Toast.makeText(
//                    applicationContext,
//                    R.string.toast_cant_set_wallpaper_message,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
    }

    /* Opens FlowerDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(wallpaper: Wallpaper) {
        actionGetContentActivityLauncher.launch(0)
    }

    /* Adds flower to flowerList when FAB is clicked. */
    private fun fabOnClick() {
//        val intent = Intent(this, AddFlowerActivity::class.java)
//        startActivityForResult(intent, newWallpaperActivityRequestCode)
    }
}
