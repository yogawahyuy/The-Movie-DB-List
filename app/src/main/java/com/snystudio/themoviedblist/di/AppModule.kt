package com.snystudio.themoviedblist.di

import android.util.Log
import com.snystudio.themoviedblist.network.RetrofitServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val baseURL="https://api.themoviedb.org/3/"


    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit):RetrofitServiceInstance{
        return retrofit.create(RetrofitServiceInstance::class.java)

    }

    @Singleton
    @Provides
    fun getRetroInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}