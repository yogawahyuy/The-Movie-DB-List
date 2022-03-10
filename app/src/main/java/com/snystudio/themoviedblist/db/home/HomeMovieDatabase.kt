package com.snystudio.themoviedblist.db.home

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HomeMovie::class],version = 1)
abstract class HomeMovieDatabase : RoomDatabase(){
    abstract fun homeMovieDao():HomeMovieDao
}