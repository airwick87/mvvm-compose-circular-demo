package com.eric.mvvm_compose_circular_demo.di.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

abstract class BaseInterceptor(private val context: Context) : Interceptor {
    abstract fun shouldIntercept(uri: String): Boolean

    fun readFromAssets(filePath: String): String =
        context.resources.assets.open(filePath).bufferedReader().use {
            it.readText()
        }
}
