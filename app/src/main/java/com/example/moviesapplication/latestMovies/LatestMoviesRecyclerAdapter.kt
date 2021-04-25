package com.example.moviesapplication.latestMovies


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moviesapplication.Results
import com.example.moviesapplication.databinding.RecyclerItemBinding
import com.example.moviesapplication.db.MovieDataBase
import com.example.moviesapplication.db.MoviesRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LatestMoviesRecyclerAdapter (private val dataSet: List<Results>, private val context: Context,
                                   private val listner : OnRecyclerClikListener,
private val moviesViewModel: LatestMoviesViewModel) :
    RecyclerView.Adapter<LatestMoviesRecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
          val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

           return ViewHolder (view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context,dataSet[position] ,listner)
    }

   inner class ViewHolder(var recyclerbinding: RecyclerItemBinding) : RecyclerView.ViewHolder(recyclerbinding.root) {

        fun bind (context: Context, result: Results, listener:OnRecyclerClikListener) {
            CoroutineScope(Dispatchers.IO).async {
            val exist = moviesViewModel.check(result.title)
                CoroutineScope(Dispatchers.Main).async {
                    recyclerbinding.favId.isChecked = exist
                }
            }

            val  imageBase = "https://image.tmdb.org/t/p/w500/"
            recyclerbinding.titleId.text= result.title
            Picasso.get().load(imageBase + result.poster_path).into(recyclerbinding.imgId)

           recyclerbinding.favId.setOnClickListener {
               listener.onFavClick(result, adapterPosition)
           }

            recyclerbinding.cardId.setOnClickListener {
                listner.onCardClick(itemView,recyclerbinding,result )
            }
        }
   }

    interface OnRecyclerClikListener{
        fun onFavClick(result: Results, position: Int)
        fun onCardClick (view: View, recyclerBinding: RecyclerItemBinding, result: Results)
    }
}