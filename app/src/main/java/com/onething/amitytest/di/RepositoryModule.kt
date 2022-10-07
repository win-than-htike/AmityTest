package com.onething.amitytest.di

import com.onething.amitytest.data.repository.TodoRepositoryImpl
import com.onething.amitytest.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  abstract fun bindTodoRepository(
    todoRepositoryImpl: TodoRepositoryImpl
  ) : TodoRepository

}