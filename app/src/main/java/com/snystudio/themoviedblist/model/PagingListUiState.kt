package com.snystudio.themoviedblist.model

import com.snystudio.themoviedblist.ui.BaseUiState

data class PagingListUiState(private val discoverMovie: DiscoverMovie):BaseUiState(){
    fun getPosterPath()="https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"+discoverMovie.posterPath
    fun getOriginalTitle()=discoverMovie.originalTitle
}
