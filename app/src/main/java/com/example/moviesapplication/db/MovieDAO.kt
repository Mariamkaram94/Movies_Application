package com.example.moviesapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesapplication.Results


@Dao
interface MovieDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie (result :Results)

    @Query ("DELETE FROM  movies WHERE movieId = :id ")
    suspend fun deleteMovie(id: Int)

    @Query ("SELECT * FROM movies")
     fun getAllFav(): LiveData<List<Results>>

    @Query("SELECT EXISTS (SELECT 1 FROM  movies WHERE movieTitle = :title)")
     fun isExist (title : String) : Boolean
}