package com.example.httpjsonparser

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.httpjsonparser.model.Song
import com.example.httpjsonparser.model.SongListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var songListAdapter: SongListAdapter
    private lateinit var listOfSongs: List<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val apiManager = (application as ParserApp).apiManager

        apiManager.getSongList { songList ->
            Log.i("lol", songList.toString())
            spinner.visibility = View.GONE
            clMiniPlayer.visibility = View.VISIBLE

            listOfSongs = songList.songs

            songListAdapter = SongListAdapter(songList.songs)
            rvMusicList.adapter = songListAdapter

            songListAdapter.onSongClickListener = {song ->
                populateActionBar(song)
            }

            btnShuffle.setOnClickListener { shuffleMusicList() }
        }
    }

    private fun populateActionBar(song: Song) {
        val songName = song.title
        val artist = song.artist
        tvActionBar.visibility = View.VISIBLE
        tvActionBar.text = getString(R.string.actionBarText).format(songName, artist)
    }

    private fun shuffleMusicList() {
        tvActionBar.visibility = View.INVISIBLE
        val shuffledList = listOfSongs.toMutableList().apply { shuffle() }
        songListAdapter.change(shuffledList)
    }
}
