package com.snystudio.themoviedblist.ui

import android.content.Context
import androidx.paging.LoadState
import com.snystudio.themoviedblist.R

data class PagingListUIState(private val loadState: LoadState):BaseUiState(){
    fun getProgressBarVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)

    fun getListVisibility() = getViewVisibility(isVisible = loadState is LoadState.NotLoading)

    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)

    fun getErrorMessage(context: Context) = if (loadState is LoadState.Error) {
        loadState.error.localizedMessage ?: context.getString(R.string.koneksigagal)
    } else ""
}
