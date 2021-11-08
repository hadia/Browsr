package com.zenjob.android.browsr.ui.movielist

import android.view.View
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.base.BaseAdapter
import com.zenjob.android.browsr.data.Show
import com.zenjob.android.browsr.databinding.ViewholderMovieItemBinding

class MovieListAdapter(private val listener: MovieListAdapterListener) : BaseAdapter<Show, ViewholderMovieItemBinding>() {

    override fun getLayoutResId(): Int = R.layout.viewholder_movie_item

    override fun bind(binding: ViewholderMovieItemBinding, show: Show, position: Int) {
        binding.model = show
        binding.position = position
        binding.title.text = show.title
        binding.releaseDate.text = android.text.format.DateFormat.format("yyyy", show.releaseDate)
        binding.rating.text = "${show.voteAverage ?: 0}"

        binding.root.setOnClickListener {
            listener.onMovieItemClick(it, position, show)
        }
    }
}

interface MovieListAdapterListener {
    fun onMovieItemClick(itemView: View, position: Int, show: Show)
}
