package su.vydramain.wallpaperengine.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Handles operations on flowersLiveData and holds details about it.
class WallpaperDataSource(resources: Resources) {
    private val wallpapersLiveData = MutableLiveData<List<Wallpaper>>()

    //  Adds flower to liveData and posts value.
    fun addWallpaper(wallpaper: Wallpaper) {
        val currentList = wallpapersLiveData.value
        if (null != currentList) {
            val updatedList = currentList.toMutableList()
            updatedList.add(updatedList.size, wallpaper)
            wallpapersLiveData.postValue(updatedList)
        } else {
            wallpapersLiveData.postValue(listOf(wallpaper))
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