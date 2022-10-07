package com.onething.amitytest.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVH<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var _data: T? = null
    val data: T
        get() = _data!!

    open fun bind(item: T) {
        _data = item
    }
}