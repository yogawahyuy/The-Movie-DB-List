package com.snystudio.themoviedblist.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.snystudio.themoviedblist.model.DiscoverMovie
import javax.inject.Inject
private const val TMDB_PAGE_INDEX = 1
private const val api_key="419b81ff6596ca606be669657bc644b3"
class PopularMoviePagingSource @Inject constructor(private val retrofitServiceInstance: RetrofitServiceInstance) : PagingSource<Int,DiscoverMovie>(){

    override fun getRefreshKey(state: PagingState<Int, DiscoverMovie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie> {
        val pageIndex=params.key?: TMDB_PAGE_INDEX
         return try {
            val response = retrofitServiceInstance.getDiscoverMoviePaging(api_key = "419b81ff6596ca606be669657bc644b3",page = pageIndex)

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if(pageIndex<response.page) pageIndex+1 else null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }


}