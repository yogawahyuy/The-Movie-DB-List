package com.snystudio.themoviedblist.network

import androidx.paging.PagingData
import com.snystudio.themoviedblist.model.DiscoverMovie
import kotlinx.coroutines.flow.Flow
interface PagingRepository {

    fun getPopularMovie():Flow<PagingData<DiscoverMovie>>

}