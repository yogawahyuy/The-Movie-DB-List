package com.snystudio.themoviedblist.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.snystudio.themoviedblist.model.NearbyPlaceModel
import com.snystudio.themoviedblist.model.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MapsRepository @Inject constructor(private val mapsServiceInstance: MapsServiceInstance) {
    fun makeApiCall(location:String,radius:Int,type:String,key:String,liveData: MutableLiveData<List<Results>>){
        val call:Call<NearbyPlaceModel> = mapsServiceInstance.getNearbyPlace(location,radius,type,key)
        Log.d("MapsRepo", "makeApiCall: "+call.request().url())
        call.enqueue(object : Callback<NearbyPlaceModel>{
            override fun onResponse(
                call: Call<NearbyPlaceModel>,
                response: Response<NearbyPlaceModel>
            ) {
               liveData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<NearbyPlaceModel>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}