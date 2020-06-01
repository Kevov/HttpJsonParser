package com.example.httpjsonparser

import android.app.Application
import com.example.httpjsonparser.manager.ApiManager
import com.example.httpjsonparser.model.Song

class ParserApp: Application() {
    lateinit var apiManager: ApiManager

    override fun onCreate() {
        super.onCreate()

        // Load managers
        apiManager = ApiManager(this)

    }
}