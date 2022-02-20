package com.snystudio.themoviedblist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.model.UpcomingMovie
import com.snystudio.themoviedblist.network.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetrofitRepository) :ViewModel() {

    private val api_key="419b81ff6596ca606be669657bc644b3"
    var liveDataList:MutableLiveData<List<DiscoverMovie>>
    var liveDataListUpcoming:MutableLiveData<List<UpcomingMovie>>
    init {
        liveDataList= MutableLiveData()
        liveDataListUpcoming= MutableLiveData()
    }

    fun getLiveDataObsevrer():MutableLiveData<List<DiscoverMovie>>{
        return liveDataList
    }
    fun loadListOfData(){
        repository.makeApiCall(api_key,liveDataList)
    }

    fun getLiveDataUpcomingObserver():MutableLiveData<List<UpcomingMovie>>{
        return liveDataListUpcoming
    }
    fun loadListOfUpcomingData(){
        repository.makeApiCallUpcoming(api_key,liveDataListUpcoming)
    }
}