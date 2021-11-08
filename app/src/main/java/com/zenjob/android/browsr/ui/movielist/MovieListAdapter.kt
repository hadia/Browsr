package com.zenjob.android.browsr.ui.movielist

import android.view.View
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.base.BaseAdapter
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.ViewholderMovieItemBinding

class MovieListAdapter(private val listener: MovieListAdapterListener) : BaseAdapter<Movie, ViewholderMovieItemBinding>() {

    override fun getLayoutResId(): Int = R.layout.viewholder_movie_item

    override fun bind(binding: ViewholderMovieItemBinding, movie: Movie, position: Int) {
        binding.model = movie
        binding.position = position
        binding.title.text = movie.title
        binding.releaseDate.text = android.text.format.DateFormat.format("yyyy", movie.releaseDate)
        binding.rating.text = "${movie.voteAverage ?: 0}"

        binding.root.setOnClickListener {
            listener.onMovieItemClick(it, position, movie)
        }
    }
}

interface MovieListAdapterListener {
    fun onMovieItemClick(itemView: View, position: Int, movie: Movie)
}
