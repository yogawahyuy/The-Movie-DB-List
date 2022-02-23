package com.snystudio.themoviedblist.model

import com.google.gson.annotations.SerializedName

data class GenreMovieList(val genres:List<GenreMovie>)
data class GenreMovie(@SerializedName("id"   ) var id   : Int?    = null,
                      @SerializedName("name" ) var name : String? = null)
