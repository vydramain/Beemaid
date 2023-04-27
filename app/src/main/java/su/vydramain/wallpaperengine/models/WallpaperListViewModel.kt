package su.vydramain.wallpaperengine.models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import su.vydramain.wallpaperengine.data.Wallpaper
import su.vydramain.wallpaperengine.data.WallpaperDataSource
import kotlin.random.Random

class WallpaperListViewModel(val dataSource: WallpaperDataSource) : ViewModel() {
    val wallpapersLiveData = dataSource.getWallpaperList()

    //  If the name and description are present, create new Wallpaper and add it to the datasource
    fun insertWallpaper(wallpaperName: String?, wallpaperDescription: String?) {
        if (wallpaperName == null || wallpaperDescription == null) {
            return
        }

        dataSource.addWallpaper(
            Wallpaper(
                Random.nextLong(),
                "",
                dataSource.getRandomWallpaperImageAsset(),
            )
        )
    }
}

class WallpaperListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WallpaperListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WallpaperListViewModel(
                dataSource = WallpaperDataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}