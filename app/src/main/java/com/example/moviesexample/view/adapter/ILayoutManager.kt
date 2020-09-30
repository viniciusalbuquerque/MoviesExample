package com.example.moviesexample.view.adapter

interface ILayoutManager {

    fun getItemCount(): Int
    fun findLastCompletelyVisibleItemPosition(): Int
}