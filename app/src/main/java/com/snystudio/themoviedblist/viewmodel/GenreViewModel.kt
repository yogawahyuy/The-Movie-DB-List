package com.snystudio.themoviedblist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snystudio.themoviedblist.model.GenreMovie
import com.snystudio.themoviedblist.network.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val retrofitRepository: RetrofitRepository):ViewModel() {
    private val api_key="419b81ff6596ca606be669657bc644b3"

    var liveDataGenre:MutableLiveData<List<GenreMovie>>

    init {
        liveDataGenre= MutableLiveData()
    }
    fun getDataObserver():MutableLiveData<List<GenreMovie>>{
        return liveDataGenre
    }
    fun getLoadDataGenre(){
        retrofitRepository.makeApiCallGenreMovie(api_key,liveDataGenre)
    }

}