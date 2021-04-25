package com.example.moviesapplication.favorites

import android.app.Application
import androidx.lifecycle.*
import com.example.moviesapplication.Results
import com.example.moviesapplication.databinding.FragmentFavoritesBinding
import com.example.moviesapplication.db.MovieDataBase
import com.example.moviesapplication.db.MoviesRepository



class FavoriteMovieViewModel ( application: Application): AndroidViewModel(application) {
    lateinit var binding: FragmentFavoritesBinding

    val movieDAO = MovieDataBase.getDataBase(application).movieDao()
    val moviesRepository= MoviesRepository(movieDAO)

    val favList: LiveData<List<Results>> = moviesRepository.displayFromDB
}