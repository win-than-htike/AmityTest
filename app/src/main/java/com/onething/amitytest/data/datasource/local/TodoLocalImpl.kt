package com.onething.amitytest.data.datasource.local

import com.onething.amitytest.data.db.dao.TodoDao
import com.onething.amitytest.data.db.entity.TodoEntity
import com.onething.amitytest.domain.datasource.local.TodoLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoLocalImpl @Inject constructor(
  private val dao: TodoDao
) : TodoLocal {
  override suspend fun saveTodoList(list: List<TodoEntity>) {
    return dao.saveTodo(list)
  }

  override fun getTodoList(): Flow<List<TodoEntity>> {
    return dao.getTodoList()
  }
}