package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.ui.MainViewModel
import com.zenjob.android.browsr.ui.detail.MovieDetailViewModel
import com.zenjob.android.browsr.ui.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { MovieListViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}
