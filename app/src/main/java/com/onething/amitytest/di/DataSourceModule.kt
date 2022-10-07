package com.onething.amitytest.di

import com.onething.amitytest.data.datasource.local.TodoLocalImpl
import com.onething.amitytest.data.datasource.remote.TodoRemoteImpl
import com.onething.amitytest.domain.datasource.local.TodoLocal
import com.onething.amitytest.domain.datasource.remote.TodoRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

  @Binds
  abstract fun bindTodoRemote(
    todoRemoteImpl: TodoRemoteImpl
  ) : TodoRemote

  @Binds
  abstract fun bindTodoLocal(
    todoLocalImpl: TodoLocalImpl
  ) : TodoLocal

}