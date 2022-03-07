package com.snystudio.themoviedblist.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.snystudio.themoviedblist.model.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagingRepositoryImpl @Inject constructor(private val retrofitServiceInstance: RetrofitServiceInstance):PagingRepository {
    override fun getPopularMovie(): Flow<PagingData<DiscoverMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {PopularMoviePagingSource(retrofitServiceInstance)}
        ).flow
    }


}