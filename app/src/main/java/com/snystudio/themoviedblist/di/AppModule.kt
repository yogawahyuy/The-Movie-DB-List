package com.snystudio.themoviedblist.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.snystudio.themoviedblist.db.home.HomeMovieDatabase
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

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(@Named("retro") retrofit: Retrofit):RetrofitServiceInstance =
         retrofit.create(RetrofitServiceInstance::class.java)


    @Provides
    @Singleton
    @Named("retro")
    fun getRetroInstance():Retrofit =
         Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


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

    @Provides
    @Singleton
    fun provideDatabase(app:Application):HomeMovieDatabase=
        Room.databaseBuilder(app,HomeMovieDatabase::class.java,"homemovie_database")
            .build()
}