package com.snystudio.themoviedblist.model

import com.google.gson.annotations.SerializedName

data class RekomendMovieList(val results: List<RekomendMovie>)
data class RekomendMovie(
    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
    @SerializedName("genre_ids"         ) var genreIds         : ArrayList<Int> = arrayListOf(),
    @SerializedName("original_language" ) var originalLanguage : String?        = null,
    @SerializedName("original_title"    ) var originalTitle    : String?        = null,
    @SerializedName("poster_path"       ) var posterPath       : String?        = null,
    @SerializedName("video"             ) var video            : Boolean?       = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("overview"          ) var overview         : String?        = null,
    @SerializedName("release_date"      ) var releaseDate      : String?        = null,
    @SerializedName("vote_count"        ) var voteCount        : Int?           = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("adult"             ) var adult            : Boolean?       = null,
    @SerializedName("popularity"        ) var popularity       : Double?        = null,
    @SerializedName("media_type"        ) var mediaType        : String?        = null)
