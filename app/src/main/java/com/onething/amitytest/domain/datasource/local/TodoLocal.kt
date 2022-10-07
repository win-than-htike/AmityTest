package com.onething.amitytest.domain.datasource.local

import com.onething.amitytest.data.db.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoLocal {

  suspend fun saveTodoList(list: List<TodoEntity>)

  fun getTodoList() : Flow<List<TodoEntity>>

}