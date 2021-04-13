package com.example.moviesapplication.networking


import com.example.moviesapplication.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionEndPoint {
    @GET("movie/popular")
    suspend fun getMovies ( @Query("api_key") apiKey:String) : Response<MoviesList>
}
