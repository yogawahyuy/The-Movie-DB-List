package com.snystudio.themoviedblist.util

import android.util.Log
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query:() -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) ->Boolean = {true}
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)){
        emit(Resource.Loading(data))
        Log.d("roomNBR", "networkBoundResource: "+fetch())
        try {
            saveFetchResult(fetch())
            Log.d("roomNBR", "networkBoundResource: "+fetch())
            query().map { Resource.Success(it) }
        }catch (t : Throwable){
            query().map { Resource.Error(t,it) }
        }
    }else{
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}