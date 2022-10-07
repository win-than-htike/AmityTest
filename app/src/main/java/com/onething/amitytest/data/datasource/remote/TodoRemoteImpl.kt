package com.onething.amitytest.data.datasource.remote

import com.onething.amitytest.data.api.ApiService
import com.onething.amitytest.data.model.TodoData
import com.onething.amitytest.domain.datasource.remote.TodoRemote
import javax.inject.Inject

class TodoRemoteImpl @Inject constructor(
  private val apiService: ApiService
) : TodoRemote {
  override suspend fun getTodoList(): List<TodoData> {
    return apiService.fetchTodoList()
  }
}