package com.snystudio.themoviedblist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.model.RekomendMovie
import com.snystudio.themoviedblist.network.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val retrofitRepository: RetrofitRepository): ViewModel(){
    private val api_key="419b81ff6596ca606be669657bc644b3"

    var liveDataSearch:MutableLiveData<List<RekomendMovie>>
    var liveDataSearchMovie:MutableLiveData<List<DiscoverMovie>>
    init {
        liveDataSearch= MutableLiveData()
        liveDataSearchMovie= MutableLiveData()
    }

    fun getDataObserver():MutableLiveData<List<RekomendMovie>>{
        return liveDataSearch
    }
    fun loadDataRekomendMovie(){
        retrofitRepository.makeApiCallRekomenMovie(api_key,liveDataSearch)
    }

    fun getDataObserverSearch():MutableLiveData<List<DiscoverMovie>>{
        return liveDataSearchMovie
    }
    fun loadDataSearchMovie(query:String){
        retrofitRepository.makeApiCallSearchMovie(api_key,query,liveDataSearchMovie)
    }
}