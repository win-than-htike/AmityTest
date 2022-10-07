package com.onething.amitytest.di

import android.app.Application
import com.onething.amitytest.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Provides
  @Singleton
  open fun provideDatabase(application: Application): AppDatabase {
    return AppDatabase.getDatabase(application.applicationContext)
  }

  @Provides
  @Singleton
  fun provideCarDao(db: AppDatabase) = db.todoDao

}