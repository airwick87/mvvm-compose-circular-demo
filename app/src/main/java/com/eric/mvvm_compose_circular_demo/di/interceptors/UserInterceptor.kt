package com.eric.mvvm_compose_circular_demo.di.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class UserInterceptor(context: Context) : BaseInterceptor(context) {

    override fun shouldIntercept(uri: String): Boolean =
        uri.endsWith("iostest/master/user.json")

    override fun intercept(chain: Interceptor.Chain): Response {
        if (shouldIntercept(chain.request().url.toUri().toString())) {
            val responseString = readFromAssets("userresponse.json")
            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString
                        .toByteArray()
                        .toResponseBody(
                            "application/json".toMediaTypeOrNull()
                        )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            return chain.proceed(chain.request())
        }
    }
}
