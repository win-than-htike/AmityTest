package com.onething.amitytest.domain.datasource.remote

import com.onething.amitytest.data.model.TodoData

interface TodoRemote {

  suspend fun getTodoList() : List<TodoData>

}