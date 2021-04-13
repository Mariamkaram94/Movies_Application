package com.example.moviesapplication.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapplication.Results
import com.example.moviesapplication.databinding.FragmentFavoritesBinding
import com.example.moviesapplication.databinding.RecyclerItemBinding
import com.example.moviesapplication.viewPagerFragment.ViewPagerDirections



class FavoritesFragment : Fragment(), FavoriteMoviesRecyclerAdapter.OnRecyclerClikListener {
    lateinit var binding: FragmentFavoritesBinding
    private val mViewModel: FavoriteMovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favRecyclerId.layoutManager = GridLayoutManager(context, 3)
        onRefresh()
        mViewModel.getAllFav(requireActivity())
        mViewModel.favList.observe(viewLifecycleOwner, Observer {
            binding.favRecyclerId.adapter = FavoriteMoviesRecyclerAdapter(it, requireActivity(),this)

            if(it.isEmpty()){
                binding.favRecyclerId.visibility = View.GONE
                binding.emptyId.emptyLayoutId.visibility = View.VISIBLE
            }else {
                binding.favRecyclerId.visibility = View.VISIBLE
                binding.emptyId.emptyLayoutId.visibility = View.GONE
            }
        })
    }

    fun onRefresh(){
        binding.swiperefreshId.setOnRefreshListener {
            mViewModel.getAllFav(requireActivity())
            binding.swiperefreshId.isRefreshing = false
        }
    }

    override fun onCardClick(view: View, recyclerBinding: RecyclerItemBinding, result: Results) {
        val extras = FragmentNavigatorExtras(recyclerBinding.cardId to "image_big")
        Navigation.findNavController(view).navigate(ViewPagerDirections.actionViewPagerToMovieDetails(result),extras)
    }
}