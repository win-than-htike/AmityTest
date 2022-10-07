package com.onething.amitytest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.onething.amitytest.R
import com.onething.amitytest.base.BaseRecyclerAdapter
import com.onething.amitytest.base.BaseVH
import com.onething.amitytest.databinding.ListItemTodoBinding
import com.onething.amitytest.domain.model.Todo
import com.onething.amitytest.presentation.adapter.TodoListAdapter.TodoVH

class TodoListAdapter() : BaseRecyclerAdapter<TodoVH, Todo>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TodoVH {
    return TodoVH(
      ListItemTodoBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  inner class TodoVH(
    private val viewBinding: ListItemTodoBinding
  ) : BaseVH<Todo>(viewBinding.root) {

    override fun bind(item: Todo) {
      super.bind(item)

      viewBinding.tvTitle.text = item.title
      viewBinding.tvStatus.text = item.status
      if (item.isDone) {
        viewBinding.tvStatus.setBackgroundResource(R.drawable.background_done)
      } else {
        viewBinding.tvStatus.setBackgroundResource(R.drawable.background_not_done)
      }

    }

  }

}