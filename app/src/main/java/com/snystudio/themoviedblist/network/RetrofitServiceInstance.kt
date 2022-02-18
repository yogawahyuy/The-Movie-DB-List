package com.snystudio.themoviedblist.network

import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.model.DiscoverMovieList
import com.snystudio.themoviedblist.model.UpComingMovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceInstance {
    @GET("discover/movie")
    fun getDiscoverMovie(@Query("api_key") api_key:String):Call<DiscoverMovieList>

    @GET("movie/upcoming")
    fun getUpcomingMovie(@Query("api_key")api_key: String):Call<UpComingMovieList>
}