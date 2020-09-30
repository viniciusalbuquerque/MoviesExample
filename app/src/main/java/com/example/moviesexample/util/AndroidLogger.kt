package com.example.moviesexample.util

import android.util.Log

class AndroidLogger: Logger{
    override fun debug(message: String) {
        Log.d("vinicius", message)
    }
}