package com.example.newsapp.di

import com.example.newsapp.data.source.DefaultElonNewsRepository
import com.example.newsapp.data.source.ElonNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindElonNewsRepository(
        elonNewsRepository: DefaultElonNewsRepository
    ): ElonNewsRepository
}
