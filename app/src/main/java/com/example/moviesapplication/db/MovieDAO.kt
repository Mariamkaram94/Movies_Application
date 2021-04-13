package com.example.moviesapplication.db

import androidx.room.*
import com.example.moviesapplication.Results


@Dao
interface MovieDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie (result :Results)

    @Query ("DELETE FROM  movies WHERE movieId = :id ")
    suspend fun deleteMovie(id: Int)

    @Query ("SELECT * FROM movies")
    fun getAllFav(): List<Results>

    @Query("SELECT EXISTS (SELECT 1 FROM  movies WHERE movieId = :id)")
    suspend fun isExist (id: Int) : Boolean
}