package com.example.moviesapplication.favorites

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.moviesapplication.Results
import com.example.moviesapplication.databinding.FragmentFavoritesBinding
import com.example.moviesapplication.db.MovieDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoriteMovieViewModel (): ViewModel () {
    var favList = MutableLiveData<List<Results>>()

    lateinit var binding: FragmentFavoritesBinding

    fun getAllFav(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val db = Room.databaseBuilder(context, MovieDataBase::class.java, "moviesDb").build()
                val favMovies = db.movieDao().getAllFav()
                viewModelScope.launch(Dispatchers.Main){
                    favList.value = favMovies
                }
            } catch (e: Exception) {
                //Toast.makeText(context, "Exception ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}