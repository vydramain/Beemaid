package su.vydramain.wallpaperengine.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.UUID

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

    // I assume, I need to replace object. Not only data in object for observer look.
    fun replaceWallpaper(wallpaper: Wallpaper) {
        val currentList = wallpapersLiveData.value
        if (null != currentList) {
            wallpapersLiveData.postValue(currentList.map {
                if (wallpaper.id == it.id) wallpaper else it
            })
        }
    }

    //  Returns wallpaper given an ID.
    fun getWallpaperForId(id: UUID): Wallpaper? {
        wallpapersLiveData.value?.let { wallpapers ->
            return wallpapers.firstOrNull { it.id.equals(id) }
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