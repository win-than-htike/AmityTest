package com.onething.amitytest.domain.model

import com.google.gson.annotations.SerializedName
import com.onething.amitytest.common.DomainModel

data class Todo(
  val isDone: Boolean,
  val status: String,
  val id: Int,
  val title: String,
  val userId: Int
) : DomainModel<Todo> {
  override fun areItemsTheSame(item: Todo): Boolean {
    return this.id == item.id
  }

  override fun areContentsTheSame(item: Todo): Boolean {
    return this == item
  }
}
