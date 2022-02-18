package com.snystudio.themoviedblist.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.model.DiscoverMovieList
import com.snystudio.themoviedblist.model.UpComingMovieList
import com.snystudio.themoviedblist.model.UpcomingMovie
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
}