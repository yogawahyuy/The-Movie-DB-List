package com.snystudio.themoviedblist.di

import com.snystudio.themoviedblist.network.PagingRepository
import com.snystudio.themoviedblist.network.PagingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PagingRepoModule {
    @Binds
    abstract fun provideRepoModule(pagingRepositoryImpl: PagingRepositoryImpl):PagingRepository
}