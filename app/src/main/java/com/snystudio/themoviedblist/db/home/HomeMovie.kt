package com.snystudio.themoviedblist.db.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homemovie")
data class HomeMovie(
    @PrimaryKey val id:Int,
    val posterPath:String,
    val originalTitle:String,
    val releaseDate:String
)
