package com.snystudio.themoviedblist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.snystudio.themoviedblist.model.DiscoverMovie
import com.snystudio.themoviedblist.network.PagingRepository
import com.snystudio.themoviedblist.network.PopularMoviePagingSource
import com.snystudio.themoviedblist.network.RetrofitServiceInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PagingListFilmViewModel @Inject constructor(retrofitServiceInstance: RetrofitServiceInstance):ViewModel(){

    val popularMovie=Pager(
        config =  PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = { PopularMoviePagingSource(retrofitServiceInstance)}
    ).flow.cachedIn(viewModelScope)
}