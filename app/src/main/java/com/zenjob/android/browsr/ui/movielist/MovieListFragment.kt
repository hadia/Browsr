package com.zenjob.android.browsr.ui.movielist

import android.view.View
import androidx.navigation.fragment.findNavController
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.base.BaseBindingFragment
import com.zenjob.android.browsr.data.Show
import com.zenjob.android.browsr.databinding.FragmentMovieListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : BaseBindingFragment<FragmentMovieListBinding>() {
    override val viewModel: MovieListViewModel by viewModel()
    override fun getLayoutResId(): Int = R.layout.fragment_movie_list

    private val movieListAdapter by lazy {
        MovieListAdapter(object : MovieListAdapterListener {
            override fun onMovieItemClick(itemView: View, position: Int, show: Show) {
                findNavController().navigate(
                    MovieListFragmentDirections
                        .actionMovieListFragmentToMovieDetailFragment(show)
                )
            }
        })
    }

    override fun onViewBound(binding: FragmentMovieListBinding) {
        binding.list.adapter = movieListAdapter

      binding.swipeContainer.setOnRefreshListener {
        viewModel. getShowsList()
        }

        viewModel.showsItemsResult.observe(
            viewLifecycleOwner,
            {
                movieListAdapter.submitList(it)
                binding.swipeContainer.setRefreshing(false)
            }
        )
    }
}
