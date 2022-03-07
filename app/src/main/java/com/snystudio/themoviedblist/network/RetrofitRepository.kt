package com.snystudio.themoviedblist.network

import android.util.Log
import androidx.appcompat.view.menu.ListMenuItemView
import androidx.lifecycle.MutableLiveData
import com.snystudio.themoviedblist.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance: RetrofitServiceInstance) {

    fun makeApiCall(apikey: String,liveDataList:MutableLiveData<List<DiscoverMovie>>){
        val call:Call<DiscoverMovieList> = retrofitServiceInstance.getDiscoverMovie(apikey)
        Log.d("retrofitrepo", "makeApiCall: "+call.request().url())
        Log.d("retrofitrepo", "makeApiCall: "+call.request().tag())
        call.enqueue(object : Callback<DiscoverMovieList>{
            override fun onResponse(
                call: Call<DiscoverMovieList>,
                response: Response<DiscoverMovieList>
            ) {
                liveDataList.postValue(response.body()?.results)
                Log.d("retrofitrepo", "onResponse: "+response.body()?.results)
                Log.d("retrofitrepo", "onResponse: "+apikey)
            }

            override fun onFailure(call: Call<DiscoverMovieList>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }
    fun makeApiCallUpcoming(apikey: String, liveDataList:MutableLiveData<List<UpcomingMovie>>){
        val call:Call<UpComingMovieList> = retrofitServiceInstance.getUpcomingMovie(apikey)

        call.enqueue(object : Callback<UpComingMovieList>{
            override fun onResponse(
                call: Call<UpComingMovieList>,
                response: Response<UpComingMovieList>
            ) {
               liveDataList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<UpComingMovieList>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }
    fun makeApiCallDetailMovie(movie_id:Int,apikey:String,liveData: MutableLiveData<DetailMovie>){
        val call:Call<DetailMovie> = retrofitServiceInstance.getDetailMovie(movie_id,apikey)

        call.enqueue(object : Callback<DetailMovie>{
            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                liveData.postValue(response.body())
                Log.d("retrofitrepo", "onResponse: "+response.body())
            }

            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
    fun makeApiCallVideosMovie(movie_id: Int,apikey: String,liveData: MutableLiveData<List<VideosMovie>>){
        val call:Call<VideosMovieList> = retrofitServiceInstance.getVideosMovie(movie_id,apikey)

        call.enqueue(object : Callback<VideosMovieList>{
            override fun onResponse(
                call: Call<VideosMovieList>,
                response: Response<VideosMovieList>
            ) {
                liveData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<VideosMovieList>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
    fun makeApiCallRekomenMovie(apikey: String,liveData:MutableLiveData<List<RekomendMovie>>){
        val call : Call<RekomendMovieList> = retrofitServiceInstance.getRekomenMovie(apikey)
        call.enqueue(object : Callback<RekomendMovieList>{
            override fun onResponse(
                call: Call<RekomendMovieList>,
                response: Response<RekomendMovieList>
            ) {
                liveData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<RekomendMovieList>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
    fun makeApiCallSearchMovie(apikey: String,query:String,liveDataList: MutableLiveData<List<DiscoverMovie>>){
        val call: Call<DiscoverMovieList> = retrofitServiceInstance.getSearchMovie(apikey,query)
        call.enqueue(object : Callback<DiscoverMovieList>{
            override fun onResponse(
                call: Call<DiscoverMovieList>,
                response: Response<DiscoverMovieList>
            ) {
                liveDataList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<DiscoverMovieList>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }
    fun makeApiCallGenreMovie(apikey: String,liveData:MutableLiveData<List<GenreMovie>>){
        val call:Call<GenreMovieList> = retrofitServiceInstance.getGenreMovie(apikey)
        call.enqueue(object : Callback<GenreMovieList>{
            override fun onResponse(
                call: Call<GenreMovieList>,
                response: Response<GenreMovieList>
            ) {
                liveData.postValue(response.body()?.genres)
            }

            override fun onFailure(call: Call<GenreMovieList>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
    fun makeApiCall(apikey: String,id_genre:Int,liveDataList:MutableLiveData<List<DiscoverMovie>>){
        val call:Call<DiscoverMovieList> = retrofitServiceInstance.getGenreListMovie(apikey,id_genre)
        call.enqueue(object : Callback<DiscoverMovieList>{
            override fun onResponse(
                call: Call<DiscoverMovieList>,
                response: Response<DiscoverMovieList>
            ) {
                liveDataList.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<DiscoverMovieList>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }
    fun makeApiCallPopularPaging(apikey: String,liveDataList: MutableLiveData<List<DiscoverMovie>>){

    }
}