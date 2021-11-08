package com.zenjob.android.browsr.network

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.method(original.method, original.body)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}
