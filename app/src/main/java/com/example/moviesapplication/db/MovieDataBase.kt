package com.example.moviesapplication.db


import androidx.room.*
import com.example.moviesapplication.Results


@Database (entities = [Results::class], version =1)

abstract class MovieDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDAO

}