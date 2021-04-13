package com.example.moviesapplication.latestMovies

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.MoviesList
import com.example.moviesapplication.Results
import com.example.moviesapplication.networking.ServiceBuilder
import kotlinx.coroutines.*
import retrofit2.Response

class LatestMoviesViewModel (): ViewModel () {
    var movieList = MutableLiveData<List<Results>>()

    fun getAllMovies(context: Context) {
        try {
            viewModelScope.async(Dispatchers.IO)  {
                val response: Response<MoviesList> =
                    ServiceBuilder.RetrofitService().getMovies("e74aee6de93d20252f4895109a12bf74")
                if(response.isSuccessful) {
                    viewModelScope.async(Dispatchers.Main) {
                        movieList.value = response.body()!!.results
                    }
                }else {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Exception ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}

