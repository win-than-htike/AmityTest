package com.onething.amitytest.data.db

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onething.amitytest.data.db.dao.TodoDao
import com.onething.amitytest.data.db.entity.TodoEntity

@Database(
  entities = [TodoEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

  public abstract val todoDao: TodoDao

  companion object {
    private var INSTANCE: AppDatabase? = null
    private var databaseName: String = "todo"
    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    fun getDatabase(context: Context): AppDatabase {
      mIsDatabaseCreated.value = false
      if (INSTANCE == null) {
        INSTANCE = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          databaseName
        ).fallbackToDestructiveMigration()
          .build()
        mIsDatabaseCreated.value = true
      }
      return INSTANCE as AppDatabase
    }

    fun destroyInstance() {
      INSTANCE = null
    }
  }
}