package com.example.moviesapplication.viewPagerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapplication.databinding.FragmentViewPagerBinding
import com.example.moviesapplication.favorites.FavoritesFragment
import com.example.moviesapplication.latestMovies.LatestMoviesFragment
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager : Fragment() {
    lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentViewPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentList = arrayListOf<Fragment>(
            LatestMoviesFragment(),
            FavoritesFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle)

        binding.viewPagerId.adapter = adapter


        // Applying TabLayout
        val tabLayout = binding.tabLayoutId
        val viewPager2 = binding.viewPagerId
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Latest Movies"
                }
                1 -> {
                    tab.text = "Favorites"
                }
            }
        }.attach()
    }
}
