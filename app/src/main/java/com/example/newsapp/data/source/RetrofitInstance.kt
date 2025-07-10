package com.example.newsapp.data.source

import com.example.newsapp.data.interceptor.ElonNewsHeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://elonmu.sh/"
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ElonNewsHeaderInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val elonNewsApi: ElonNewsService by lazy {
        retrofit.create(ElonNewsService::class.java)
    }
}