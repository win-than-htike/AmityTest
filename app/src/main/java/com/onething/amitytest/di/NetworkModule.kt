package com.onething.amitytest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.onething.amitytest.BuildConfig
import com.onething.amitytest.data.api.ApiService
import com.onething.amitytest.utils.URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  open fun provideGson() = GsonBuilder().create()

  @Provides
  @Singleton
  fun provideLoggingInterceptor() : HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
      logger.level = HttpLoggingInterceptor.Level.BODY
    }
    return logger
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
  ) = OkHttpClient.Builder()
    .connectTimeout(60, SECONDS)
    .readTimeout(60, SECONDS)
    .writeTimeout(60, SECONDS)
    .addInterceptor(loggingInterceptor)
    .build()

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) : Retrofit {
    return Retrofit.Builder()
      .baseUrl(URL.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
  }

  @Provides
  fun provideApiService(retrofit: Retrofit) : ApiService =
    retrofit.create(ApiService::class.java)

}