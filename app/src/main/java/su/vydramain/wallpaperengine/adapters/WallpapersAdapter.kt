package su.vydramain.wallpaperengine.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.data.Wallpaper

class WallpapersAdapter(private val onClick: (Wallpaper) -> Unit) :
    ListAdapter<Wallpaper, WallpapersAdapter.WallpapersPreViewHolder>(WallpaperDiffCallback) {

    //  ViewHolder for Wallpaper, takes in the inflated view and the onClick behavior.
    class WallpapersPreViewHolder(itemView: View, val onClick: (Wallpaper) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var wallpaperPreviewPath: TextView =
            itemView.findViewById(R.id.wallpaper_settings_path)
        private var wallpaperPreviewImage: ImageView =
            itemView.findViewById(R.id.wallpaper_preview)
        private var wallpaperPreviewImageChooseButton: Button =
            itemView.findViewById(R.id.wallpaper_choose_button)
        private var currentWallpaper: Wallpaper? = null

        init {
            itemView.setOnClickListener {
                currentWallpaper?.let {
                    onClick(it)
                }
            }
        }

        //      Bind wallpaper path and image.
        fun bind(wallpaper: Wallpaper) {
            currentWallpaper = wallpaper

            wallpaperPreviewPath.text = wallpaper.path
            if (Uri.EMPTY != wallpaper.imageUri) {
                wallpaperPreviewImage.setImageURI(wallpaper.imageUri)
            } else {
                wallpaperPreviewImage.setImageResource(R.drawable.ic_menu_gallery)
            }

            wallpaperPreviewImageChooseButton.setOnClickListener {
                currentWallpaper?.let {
                    onClick(it)
                }
            }
        }
    }

    //  Creates and inflates view and return WallpaperPreViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersPreViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_pre_view, parent, false)

        return WallpapersPreViewHolder(view, onClick)
    }

    //  Gets current wallpaper and uses it to bind view.
    override fun onBindViewHolder(holder: WallpapersPreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object WallpaperDiffCallback : DiffUtil.ItemCallback<Wallpaper>() {
    override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
        return oldItem.id == newItem.id
    }
}
