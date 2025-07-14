package com.example.newsapp.di

import com.example.newsapp.data.interceptor.ElonNewsHeaderInterceptor
import com.example.newsapp.data.source.ElonNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://elonmu.sh/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideElonNewsHeaderInterceptor(): ElonNewsHeaderInterceptor {
        return ElonNewsHeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: ElonNewsHeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ElonNewsHeaderInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideElonNewsService(
        retrofit: Retrofit
    ): ElonNewsService {
        return retrofit.create(ElonNewsService::class.java)
    }
}
