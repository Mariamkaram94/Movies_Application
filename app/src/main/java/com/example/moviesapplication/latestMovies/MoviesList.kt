
package com.example.moviesapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class MoviesList(
    val page: Int,
    val results: List<Results>,
    val total_pages: Int,
    val total_results: Int
)



@Entity(tableName= "movies")

data class Results(

    @PrimaryKey(autoGenerate = true)
    var idInTable: Int= 0,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "backdropPath")
    val backdrop_path: String,

//    @ColumnInfo(name = "genreIds")
//    val genre_ids: List<Int>,

    @ColumnInfo(name = "movieId")
    val id: Int,

    @ColumnInfo(name = "originalLanguage")
    val original_language: String,

    @ColumnInfo (name = "movieOriginalTitle")
    val original_title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo (name = "moviePoster")
    val poster_path: String,

    @ColumnInfo(name = "releaseDate")
    val release_date: String,

    @ColumnInfo(name = "movieTitle")
    val title: String,

    @ColumnInfo(name = "movieVideo")
    val video: Boolean,

    @ColumnInfo(name = "voteAverage")
    val vote_average: Double,

    @ColumnInfo(name = "voteCount")
    val vote_count: Int
) : Serializable
