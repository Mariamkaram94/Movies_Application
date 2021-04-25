package com.example.moviesapplication.db


import android.content.Context
import androidx.room.*
import com.example.moviesapplication.Results


@Database (entities = [Results::class], version =1)

abstract class MovieDataBase: RoomDatabase() {

    abstract fun movieDao(): MovieDAO

    companion object {
        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getDataBase (context: Context) : MovieDataBase {
            return INSTANCE ?:synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    MovieDataBase::class.java,
                    "movies_database").build()
                INSTANCE= instance
              return instance
            }
        }
    }
}
