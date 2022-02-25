package com.snystudio.themoviedblist.di

import android.util.Log
import com.snystudio.themoviedblist.network.MapsServiceInstance
import com.snystudio.themoviedblist.network.RetrofitServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val baseURL="https://api.themoviedb.org/3/"


    @Singleton
    @Provides
    fun getRetrofitServiceInstance(@Named("retro") retrofit: Retrofit):RetrofitServiceInstance{
        return retrofit.create(RetrofitServiceInstance::class.java)

    }

    @Singleton
    @Provides
    @Named("retro")
    fun getRetroInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val baseURLMaps="https://maps.googleapis.com/"

    @Singleton
    @Provides

    fun getMapsInstance(@Named("maps")retrofit2: Retrofit): MapsServiceInstance {
        return retrofit2.create(MapsServiceInstance::class.java)
    }

    @Singleton
    @Provides
    @Named("maps")
    fun getMapsRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURLMaps)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}