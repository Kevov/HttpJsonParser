package com.example.httpjsonparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.httpjsonparser.model.Song
import com.example.httpjsonparser.model.SongListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var songListAdapter: SongListAdapter
    private lateinit var listOfSongs: List<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = (application as ParserApp)
        val apiManager = app.apiManager
        tvUsername.text = app.username

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

        btnChangeUser.setOnClickListener { changeUser() }
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

    private fun changeUser() {
        Log.i("dotify", "change user")
        when {
            btnChangeUser.text == "Change User" -> {
                btnChangeUser.text = "Apply"
                changeUsernameEditVisibility(View.INVISIBLE, View.VISIBLE)
            }
            etEditUsername.text.toString() == "" -> {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else -> {
                (application as ParserApp).username = etEditUsername.text.toString()
                tvUsername.text = (application as ParserApp).username
                btnChangeUser.text = "Change User"
                changeUsernameEditVisibility(View.VISIBLE, View.INVISIBLE)
            }
        }
    }

    private fun changeUsernameEditVisibility(username: Int, editName: Int) {
        tvUsername.visibility = username
        etEditUsername.visibility = editName
    }
}
