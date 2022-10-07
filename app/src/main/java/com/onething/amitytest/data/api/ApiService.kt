package com.onething.amitytest.data.api

import com.onething.amitytest.data.model.TodoData
import com.onething.amitytest.utils.URL
import retrofit2.http.GET

interface ApiService {

  @GET(URL.GET_TODO_LIST)
  suspend fun fetchTodoList() : List<TodoData>

}