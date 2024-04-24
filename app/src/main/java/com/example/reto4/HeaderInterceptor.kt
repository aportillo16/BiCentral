package com.example.reto4

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "x-api-key","81818BBF-C772-411D-9BFA-3CAFA12D6077"
            )
            .build()

        return chain.proceed(request)
    }
}