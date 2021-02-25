package com.example.moviesexample.util

import android.util.Log

class AndroidLogger: Logger {
    override fun debug(tag: String, message: String) {
        Log.d("vinicius", "[$tag] $message")
    }
}