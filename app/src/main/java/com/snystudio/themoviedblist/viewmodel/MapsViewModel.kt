package com.snystudio.themoviedblist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.snystudio.themoviedblist.model.Results
import com.snystudio.themoviedblist.network.MapsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val mapsRepository: MapsRepository):ViewModel() {
    private val key="AIzaSyB1oi8GWu1VFthN1qe45lSq8uTZgmcEt1w"

    var liveData:MutableLiveData<List<Results>>

    init {
        liveData= MutableLiveData()
    }

    fun getDataObserver():MutableLiveData<List<Results>>{
        return liveData
    }
    fun loadDataMaps(location:String,radius:Int,type:String){
        mapsRepository.makeApiCall(location,radius,type,key,liveData)
    }
}