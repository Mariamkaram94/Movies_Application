package com.example.moviesapplication.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    fun RetrofitService() : ConnectionEndPoint {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ConnectionEndPoint::class.java)
    }

}
