package com.onething.amitytest.base

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.onething.amitytest.common.DomainModel

abstract class BaseRecyclerAdapter<VH : BaseVH<T>, T : DomainModel<T>> :
    RecyclerView.Adapter<VH>() {

    private var list: MutableList<T> = mutableListOf()

    init {
        list = ArrayList()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItems() : List<T> {
        return list
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

    fun update(newData: List<T>?) {
        val oldList = list
        list = newData.orEmpty().toMutableList()
        if (oldList.isEmpty()) {
            notifyDataSetChanged()
        } else {
            val diffResult = DiffUtil.calculateDiff(BaseDiffCallback(oldList, list))
            diffResult.dispatchUpdatesTo(this)
        }
    }
}

class BaseDiffCallback<T : DomainModel<T>>(
    private val oldItems: List<T>,
    private val newItems: List<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].areItemsTheSame(newItems[newItemPosition])
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    @Nullable override fun getChangePayload(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].areContentsTheSame(newItems[newItemPosition])
    }
}
