package com.example.newsapp.data.source

import android.util.Log
import com.example.newsapp.data.ElonNewsResponse
import jakarta.inject.Inject
import retrofit2.HttpException

interface ElonNewsRepository {
    suspend fun getElonNews(): Result<ElonNewsResponse>
}

class DefaultElonNewsRepository @Inject constructor(
    private val api: ElonNewsService
) : ElonNewsRepository {
    override suspend fun getElonNews(): Result<ElonNewsResponse> {
        return try {
            val response = api.getElonNews()
            Log.d("API_DEBUG", "Null body. Code: ${response.code()}, Message: ${response.message()}")


            if (response.isSuccessful) {
                if (response != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(Exception("An unexpected error occurred: ${e.message}", e))
        }
    }
}
