package su.vydramain.wallpaperengine.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.vydramain.wallpaperengine.R

// Handles operations on flowersLiveData and holds details about it.
class WallpaperDataSource(resources: Resources) {
    private val initialWallpaperList = listOf(
        Wallpaper(
            id = 0,
            path = "",
            image = R.drawable.ic_menu_gallery
        )
    )

    private val wallpapersLiveData = MutableLiveData(initialWallpaperList)

    //  Adds flower to liveData and posts value.
    fun addWallpaper(wallpaper: Wallpaper) {
        val currentList = wallpapersLiveData.value
        if (null != currentList) {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, wallpaper)
            wallpapersLiveData.postValue(updatedList)
        }
    }

    //  Removes flower from liveData and posts value.
    fun removeWallpaper(wallpaper: Wallpaper) {
        val currentList = wallpapersLiveData.value
        if (null != currentList) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(wallpaper)
            wallpapersLiveData.postValue(updatedList)
        }
    }

    //  Returns flower given an ID.
    fun getWallpaperForId(id: Long): Wallpaper? {
        wallpapersLiveData.value?.let { wallpapers ->
            return wallpapers.firstOrNull { it.id == id }
        }
        return null
    }

    fun getWallpaperList(): LiveData<List<Wallpaper>> {
        return wallpapersLiveData
    }

    //  Returns a random flower asset for flowers that are added.
    fun getRandomWallpaperImageAsset(): Int? {
        val randomNumber = (initialWallpaperList.indices).random()
        return initialWallpaperList[randomNumber].image
    }

    companion object {
        private var INSTANCE: WallpaperDataSource? = null

        fun getDataSource(resources: Resources): WallpaperDataSource {
            return synchronized(WallpaperDataSource::class) {
                val newInstance = INSTANCE ?: WallpaperDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}