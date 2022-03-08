package com.snystudio.themoviedblist.network

import com.snystudio.themoviedblist.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {
    @GET("discover/movie")
    fun getDiscoverMovie(@Query("api_key") api_key:String):Call<DiscoverMovieList>

    @GET("movie/upcoming")
    fun getUpcomingMovie(@Query("api_key")api_key: String):Call<UpComingMovieList>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movie_id:Int,@Query("api_key")api_key : String):Call<DetailMovie>

    @GET("movie/{movie_id}/videos")
    fun getVideosMovie(@Path("movie_id")movie_id: Int,@Query("api_key")api_key: String):Call<VideosMovieList>

    @GET("trending/all/day")
    fun getRekomenMovie(@Query("api_key")api_key: String): Call<RekomendMovieList>

    @GET("search/movie")
    fun getSearchMovie(@Query("api_key")api_key: String,@Query("query")query:String):Call<DiscoverMovieList>

    @GET("genre/movie/list")
    fun getGenreMovie(@Query("api_key")api_key: String):Call<GenreMovieList>

    @GET("discover/movie")
    fun getGenreListMovie(@Query("api_key")api_key: String,@Query("with_genres")id_genre:Int):Call<DiscoverMovieList>

    @GET("discover/movie")
    suspend fun getDiscoverMoviePaging(@Query("api_key") api_key:String,@Query("page") page:Int):DiscoverMovieList



}