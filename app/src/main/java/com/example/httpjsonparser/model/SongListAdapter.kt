package com.example.httpjsonparser.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.httpjsonparser.R
import com.squareup.picasso.Picasso

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    var onSongClickListener: ((song: Song) -> Unit)? = null

    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvSongName by lazy { itemView.findViewById<TextView>(R.id.tvSongName) }
        private val tvArtist by lazy {itemView.findViewById<TextView>(R.id.tvArtist)}
        private val ivAlbumCover by lazy {itemView.findViewById<ImageView>(R.id.ivAlbumCover)}
        fun bind(song: Song) {
            tvSongName.text = song.title
            tvArtist.text = song.artist
            Picasso.get().load(song.smallImageURL).into(ivAlbumCover)
            ivAlbumCover.contentDescription = "Album cover for the song ${song.title}"

            itemView.setOnClickListener{
                onSongClickListener?.invoke(song)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        holder.bind(song)
    }

    fun change(newListOfSong: List<Song>) {
        listOfSongs = newListOfSong
        notifyDataSetChanged()
    }
}