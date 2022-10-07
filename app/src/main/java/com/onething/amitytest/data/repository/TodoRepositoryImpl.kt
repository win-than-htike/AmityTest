package com.onething.amitytest.data.repository

import com.onething.amitytest.data.db.entity.TodoEntity
import com.onething.amitytest.data.db.entity.toDomain
import com.onething.amitytest.data.model.toEntity
import com.onething.amitytest.domain.datasource.local.TodoLocal
import com.onething.amitytest.domain.datasource.remote.TodoRemote
import com.onething.amitytest.domain.model.Todo
import com.onething.amitytest.domain.repository.TodoRepository
import com.onething.amitytest.utils.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
  private val remote: TodoRemote,
  private val local: TodoLocal
) : TodoRepository {
  override suspend fun saveTodoList(): Either<Throwable, Unit> {
    return try {
      val result = remote.getTodoList()
      local.saveTodoList(result.map { it.toEntity() })
      Either.Right(Unit)
    } catch (e: Throwable) {
      Either.Left(e)
    }
  }

  override fun getTodoList(): Flow<List<Todo>> {
    return local.getTodoList().map { entity ->
      entity.map { it.toDomain() }
    }
  }
}