package com.example.moviesexample.view.listeners

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesexample.util.Logger

class EndlessScrollListener(
    private val layoutManager: RecyclerView.LayoutManager,
    private val scrollCallback: EndlessScrollCallback,
    private val logger: Logger)
    : RecyclerView.OnScrollListener() {

    private val TAG = "EndlessScrollListener"

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        logger.debug(TAG, "onScrolled! dx: $dx, dy: $dy")
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemIndex = (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

        logger.debug(TAG, "totalItemCount: $totalItemCount, lastVisibleItem: $lastVisibleItemIndex")

        if (lastVisibleItemIndex + 1 >= totalItemCount) {
            logger.debug(TAG, "load next page")
            scrollCallback.loadNext()
        }
    }
}