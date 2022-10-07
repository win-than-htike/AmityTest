package com.onething.amitytest.domain.usecase

import com.onething.amitytest.domain.repository.TodoRepository
import com.onething.amitytest.utils.Either
import javax.inject.Inject

class SaveTodoListUseCase @Inject constructor(
  private val todoRepository: TodoRepository
) {

  suspend operator fun invoke() : Either<Throwable, Unit> {
    return todoRepository.saveTodoList()
  }

}