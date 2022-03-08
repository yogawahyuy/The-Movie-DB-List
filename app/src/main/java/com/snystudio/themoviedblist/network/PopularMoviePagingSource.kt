package com.snystudio.themoviedblist.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.snystudio.themoviedblist.model.DiscoverMovie
import javax.inject.Inject
private const val TMDB_PAGE_INDEX = 1
private const val api_key="419b81ff6596ca606be669657bc644b3"
class PopularMoviePagingSource(private val retrofitServiceInstance: RetrofitServiceInstance) : PagingSource<Int,DiscoverMovie>(){

    override fun getRefreshKey(state: PagingState<Int, DiscoverMovie>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie> {
        val pageIndex=params.key?: TMDB_PAGE_INDEX
         return try {
            val response = retrofitServiceInstance.getDiscoverMoviePaging(api_key,pageIndex)
            // Log.d("pagingsource", "load: "+response.results)
            LoadResult.Page(
                data = response.results,
                prevKey = if (pageIndex== TMDB_PAGE_INDEX)null else pageIndex.minus(1),
                nextKey = if(response.results.isEmpty()) null else pageIndex.plus(1)
            )

        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }


}