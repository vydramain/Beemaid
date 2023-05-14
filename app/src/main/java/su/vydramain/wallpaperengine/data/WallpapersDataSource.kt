package su.vydramain.wallpaperengine.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Handles operations on wallpapersLiveData and holds details about it.
class WallpapersDataSource(resources: Resources) {
    private val wallpapersLiveData = MutableLiveData<List<Wallpaper>>()

    //  Adds wallpaper to liveData and posts value.
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

    //  Removes wallpaper from liveData and posts value.
    fun removeWallpaper(wallpaper: Wallpaper) {
        val currentList = wallpapersLiveData.value
        if (null != currentList) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(wallpaper)
            wallpapersLiveData.postValue(updatedList)
        }
    }

    fun replaceWallpaper(wallpaper: Wallpaper) {
        val currentList = wallpapersLiveData.value
        if (null != currentList) {
            currentList.forEach {
                if (it.id == wallpaper.id) {
                    it.path = wallpaper.path
                    it.imageUri = wallpaper.imageUri
                }
            }
            wallpapersLiveData.postValue(currentList)
        }
    }

    //  Returns wallpaper given an ID.
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
        private var INSTANCE: WallpapersDataSource? = null

        fun getDataSource(resources: Resources): WallpapersDataSource {
            return synchronized(WallpapersDataSource::class) {
                val newInstance = INSTANCE ?: WallpapersDataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}