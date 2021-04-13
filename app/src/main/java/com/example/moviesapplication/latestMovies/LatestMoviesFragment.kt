package com.example.moviesapplication.latestMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.moviesapplication.Results
import com.example.moviesapplication.databinding.FragmentLatestMoviesBinding
import com.example.moviesapplication.databinding.RecyclerItemBinding
import com.example.moviesapplication.db.MovieDataBase
import com.example.moviesapplication.viewPagerFragment.ViewPagerDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


class LatestMoviesFragment : Fragment(),LatestMoviesRecyclerAdapter.OnRecyclerClikListener {

    lateinit var binding: FragmentLatestMoviesBinding
    private val mViewModel: LatestMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLatestMoviesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerId.layoutManager = GridLayoutManager(context, 3)
        mViewModel.getAllMovies(requireActivity())
        mViewModel.movieList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.recyclerId.adapter = LatestMoviesRecyclerAdapter(it, requireActivity(), this)
        })
    }

    override fun onFavClick(result: Results, position: Int) {
        CoroutineScope(Dispatchers.IO).async {
            val db = Room.databaseBuilder(requireContext(), MovieDataBase::class.java, "moviesDb").build()
            CoroutineScope(Dispatchers.Main).async {

                val adult = result.adult
                val backdropPath = result.backdrop_path
                val movieId = result.id
                val originalLanguage = result.original_language
                val movieOriginalTitle = result.original_title
                val overview = result.overview
                val popularity = result.popularity
                val moviePoster = result.poster_path
                val releaseDate = result.release_date
                val movieTitle = result.title
                val movieVideo= result.video
                val voteAverage = result.vote_average
                val voteCount = result.vote_count

                val movie = Results(0,adult,backdropPath,movieId,originalLanguage,
                    movieOriginalTitle,overview,popularity, moviePoster,releaseDate,movieTitle,movieVideo,voteAverage,voteCount)


                if(db.movieDao().isExist(result.id)){
                    db.movieDao().deleteMovie(result.id)
                }else{
                    db.movieDao().addMovie(movie)
                  //  Toast.makeText(context, "Added To Favorites", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //Applying Shared Element Animation
    override fun onCardClick(view: View, recyclerBinding: RecyclerItemBinding, result: Results) {
        val extras = FragmentNavigatorExtras(recyclerBinding.cardId to "image_big")
        Navigation.findNavController(view).navigate(ViewPagerDirections.actionViewPagerToMovieDetails(result),extras)
    }
}
