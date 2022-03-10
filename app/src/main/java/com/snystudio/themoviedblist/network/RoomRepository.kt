package com.snystudio.themoviedblist.network

import android.util.Log
import com.snystudio.themoviedblist.db.home.HomeMovieDatabase
import com.snystudio.themoviedblist.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val api: RetrofitServiceInstance,
    private val db : HomeMovieDatabase) {

    private val api_key="419b81ff6596ca606be669657bc644b3"
    private val homeMovieDao = db.homeMovieDao()

    fun getHomeMovie() = networkBoundResource(
        query = {
            homeMovieDao.getAllMovie()
        },
        fetch = {
            delay(2000)
            Log.d("room repo", "getHomeMovie: "+api.getDiscoverMovieRoom(api_key))
            api.getDiscoverMovieRoom(api_key)
                },
        saveFetchResult = { homeMovie ->
            db.run {
                homeMovieDao.deleteAllMovie()
                homeMovieDao.insertMovie(homeMovie)
            }
        }
    )
}