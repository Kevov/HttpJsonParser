package com.example.httpjsonparser.model

data class SongList(
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>
)