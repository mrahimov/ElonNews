package com.example.newsapp.data.source

import com.example.newsapp.data.ElonNewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ElonNewsService {

    @GET("api")
    suspend fun getElonNews(): Response<ElonNewsResponse>
}