package com.snystudio.themoviedblist.db.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeMovieDao {

    @Query("SELECT * FROM homemovie")
    fun getAllMovie(): Flow<List<HomeMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(homeMovie: List<HomeMovie>)

    @Query("DELETE FROM homemovie")
    suspend fun deleteAllMovie()

}