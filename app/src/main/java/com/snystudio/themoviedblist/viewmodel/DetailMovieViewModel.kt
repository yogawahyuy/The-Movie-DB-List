package com.snystudio.themoviedblist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snystudio.themoviedblist.model.DetailMovie
import com.snystudio.themoviedblist.network.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val retrofitRepository: RetrofitRepository):ViewModel(){
    private val api_key="419b81ff6596ca606be669657bc644b3"
    var liveDataDetail:MutableLiveData<DetailMovie>
    init {
        liveDataDetail= MutableLiveData()
    }

    fun getDataObsever():MutableLiveData<DetailMovie>{
        return liveDataDetail
    }
    fun loadOfData(movie_id:Int){
        retrofitRepository.makeApiCallDetailMovie(movie_id,api_key,liveDataDetail)
    }

}