package com.onething.amitytest.domain.repository

import com.onething.amitytest.data.db.entity.TodoEntity
import com.onething.amitytest.domain.model.Todo
import com.onething.amitytest.utils.Either
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

  suspend fun saveTodoList() : Either<Throwable, Unit>

  fun getTodoList() : Flow<List<Todo>>

}