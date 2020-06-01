package com.example.httpjsonparser.manager

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.httpjsonparser.model.SongList
import com.google.gson.Gson

class ApiManager(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getSongList(onSuccess: (SongList) -> Unit) {
        val emailURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/musiclibrary.json"

        val request = StringRequest(
            Request.Method.GET, emailURL, {response ->
                val gson = Gson()
                val songList = gson.fromJson(response, SongList::class.java)
                onSuccess(songList)
            }, 
            {
                Log.i("lol", "fail")
            }
        )

        queue.add(request)
    }
}