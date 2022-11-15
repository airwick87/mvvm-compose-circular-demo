package com.eric.mvvm_compose_circular_demo.di.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class NonAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder()
                .addHeader("User-Agent", "App")
                .build()
        return chain.proceed(request)
    }
}
