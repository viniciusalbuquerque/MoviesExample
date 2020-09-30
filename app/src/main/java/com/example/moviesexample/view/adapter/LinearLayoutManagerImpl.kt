package com.example.moviesexample.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager

class LinearLayoutManagerImpl(private val layoutManager: LinearLayoutManager): ILayoutManager {

    override fun getItemCount() = layoutManager.itemCount

    override fun findLastCompletelyVisibleItemPosition() =
        layoutManager.findLastCompletelyVisibleItemPosition()
}