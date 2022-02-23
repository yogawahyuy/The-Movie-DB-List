package com.snystudio.themoviedblist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.network.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListFilmViewModel @Inject constructor(private val retrofitRepository: RetrofitRepository):ViewModel(){
    private val api_key="419b81ff6596ca606be669657bc644b3"

    var liveData:MutableLiveData<List<DiscoverMovie>>

    init {
        liveData= MutableLiveData()
    }
    fun getLiveDataObsevrer():MutableLiveData<List<DiscoverMovie>>{
        return liveData
    }
    fun loadListOfData(id_genre:Int){
        retrofitRepository.makeApiCall(api_key,id_genre,liveData)
    }

}