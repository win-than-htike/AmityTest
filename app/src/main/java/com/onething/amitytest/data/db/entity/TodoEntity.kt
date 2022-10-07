package com.onething.amitytest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onething.amitytest.domain.model.Todo

@Entity(tableName = "todo")
data class TodoEntity(
  val status: String,
  @PrimaryKey
  val id: Int,
  val title: String,
  val userId: Int,
  val isDone: Boolean,
)

fun TodoEntity.toDomain() : Todo {
  return Todo(
    status = this.status,
    id = this.id,
    title = this.title,
    userId = this.userId,
    isDone = this.isDone
  )
}
