package com.onething.amitytest.domain.usecase

import com.onething.amitytest.common.ErrorEntity
import com.onething.amitytest.domain.model.Todo
import com.onething.amitytest.domain.repository.TodoRepository
import com.onething.amitytest.utils.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(
  private val todoRepository: TodoRepository
) {

  operator fun invoke() : Flow<List<Todo>> {
    return todoRepository.getTodoList()
  }

}