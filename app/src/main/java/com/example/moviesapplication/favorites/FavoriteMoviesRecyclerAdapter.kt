package com.example.moviesapplication.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moviesapplication.Results
import com.example.moviesapplication.databinding.RecyclerItemBinding
import com.example.moviesapplication.db.MovieDataBase
import com.example.moviesapplication.latestMovies.LatestMoviesRecyclerAdapter
import com.example.moviesapplication.viewPagerFragment.ViewPagerDirections
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class FavoriteMoviesRecyclerAdapter(private val dataSet: List<Results>,
                                    private val context: Context,
                                     private val listner: OnRecyclerClikListener ):
        RecyclerView.Adapter<FavoriteMoviesRecyclerAdapter.ViewHolder>() {



    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, dataSet[position], listner)
    }

    inner class ViewHolder(var recyclerbinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(recyclerbinding.root) {

        fun bind(context: Context, result: Results, listner: OnRecyclerClikListener) {

            recyclerbinding.favId.isChecked = true
            recyclerbinding.favId.setOnClickListener {
                CoroutineScope(Dispatchers.IO).async {
                    val db = Room.databaseBuilder(context, MovieDataBase::class.java, "movies_database").build()
                    CoroutineScope(Dispatchers.Main).async {
                         db.movieDao().deleteMovie(result.id)
                    }
                }
            }

            val imageBase: String = "https://image.tmdb.org/t/p/w500/"
            recyclerbinding.titleId.text = result.title
            Picasso.get().load(imageBase + result.poster_path).into(recyclerbinding.imgId)

            recyclerbinding.cardId.setOnClickListener {
                listner.onCardClick(itemView,recyclerbinding,result )
            }
        }
    }

    interface OnRecyclerClikListener{
        fun onCardClick (view: View, recyclerBinding: RecyclerItemBinding, result: Results)
    }
}