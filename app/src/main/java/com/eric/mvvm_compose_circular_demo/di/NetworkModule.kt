package com.eric.mvvm_compose_circular_demo.di

import android.content.Context
import com.eric.data.services.UserService
import com.eric.mvvm_compose_circular_demo.BuildConfig
import com.eric.mvvm_compose_circular_demo.di.interceptors.NonAuthInterceptor
import com.eric.mvvm_compose_circular_demo.di.interceptors.UserInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private fun getLogger() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(NonAuthInterceptor())
            if (BuildConfig.DEBUG) {
                addInterceptor(getLogger())
                addInterceptor(UserInterceptor(context))
            }
            connectTimeout(30, TimeUnit.SECONDS)
        }
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}
