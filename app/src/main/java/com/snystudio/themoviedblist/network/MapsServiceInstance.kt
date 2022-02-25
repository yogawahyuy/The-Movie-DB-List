package com.snystudio.themoviedblist.network

import com.google.android.gms.maps.model.LatLng
import com.snystudio.themoviedblist.model.NearbyPlaceModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsServiceInstance {
    @GET("maps/api/place/nearbysearch/json")
    fun getNearbyPlace(@Query("location") location:String, @Query("radius")radius:Int, @Query("type")type:String, @Query("key")api_key: String): Call<NearbyPlaceModel>

}