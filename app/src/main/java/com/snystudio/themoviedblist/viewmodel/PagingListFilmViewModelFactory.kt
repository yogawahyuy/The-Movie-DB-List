package com.snystudio.themoviedblist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snystudio.themoviedblist.network.RetrofitServiceInstance
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PagingListFilmViewModelFactory @Inject constructor(private val retrofitServiceInstance1: RetrofitServiceInstance):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PagingListFilmViewModel(retrofitServiceInstance1) as T
    }
}