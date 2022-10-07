package com.onething

import android.app.Application
import android.app.appsearch.SearchResult.MatchInfo.Builder
import com.onething.amitytest.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest
import timber.log.Timber.Forest.plant

@HiltAndroidApp
class App : Application() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    }
  }

}