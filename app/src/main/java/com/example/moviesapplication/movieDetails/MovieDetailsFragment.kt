package com.example.moviesapplication.movieDetails

import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.style.ForegroundColorSpan
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.FragmentMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailsBinding
    private val mViewModel : MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(R.transition.fade_in)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  imageBase:String = "https://image.tmdb.org/t/p/w500/"
        binding.titleId.text = args.args.title

        // to Color subString inside the TextView
        val langspannableString :SpannableString = SpannableString (getString(R.string.language,args.args.original_language))
        var fColor = ForegroundColorSpan(Color.RED)
        langspannableString.setSpan(fColor,0,9,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.languageId.text = langspannableString

        val datespannableString :SpannableString = SpannableString (getString(R.string.released,args.args.release_date))
        var bColor = ForegroundColorSpan(Color.RED)
        datespannableString.setSpan(bColor,0,14,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.releasedId.text = datespannableString

        binding.overviewId.text = args.args.overview
        binding.ratingBarId.rating = (args.args.vote_average/2).toString().toFloat()
        Picasso.get().load(imageBase + args.args.poster_path).into(binding.imgId)
        Picasso.get().load(imageBase + args.args.backdrop_path).into(binding.movieCoverId)
    }
}