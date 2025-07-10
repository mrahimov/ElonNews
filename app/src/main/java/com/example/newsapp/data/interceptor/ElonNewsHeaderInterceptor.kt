package com.example.newsapp.data.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class ElonNewsHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return try {
            chain.proceed(request)
        } catch (e: Exception) {
            Log.e("Interceptor", "Network error", e)

            Response.Builder()
                .request(request)
                .code(599)
                .message("Service Network Request Error")
                .body("{\"error\":\"${e.message}\"}".toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull()))
                .protocol(Protocol.HTTP_2)
                .build()
        }
    }
}