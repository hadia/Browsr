package com.zenjob.android.browsr.ui.detail

import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.base.BaseBindingFragment
import com.zenjob.android.browsr.databinding.FragmentMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseBindingFragment<FragmentMovieDetailsBinding>() {
    override val viewModel: MovieDetailViewModel by viewModel()
    override fun getLayoutResId(): Int = R.layout.fragment_movie_details

    override fun onViewBound(binding: FragmentMovieDetailsBinding) {
        MovieDetailFragmentArgs.fromBundle(requireArguments()).showItem.apply {
            binding.title.text = title
            binding.releaseDate.text =
                android.text.format.DateFormat.format("yyyy", releaseDate)
            binding.rating.text = "${voteAverage ?: 0}"
            binding.description.text = overview
        }
    }
}
