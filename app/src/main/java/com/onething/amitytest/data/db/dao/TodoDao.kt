package com.onething.amitytest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onething.amitytest.data.db.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveTodo(list: List<TodoEntity>)

  @Query("select * from todo")
  fun getTodoList() : Flow<List<TodoEntity>>

}