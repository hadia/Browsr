package com.zenjob.android.browsr.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zenjob.android.browsr.base.BaseViewModel
import com.zenjob.android.browsr.data.Show
import com.zenjob.android.browsr.network.checkResponse
import com.zenjob.android.browsr.repository.ShowsRepository
import kotlinx.coroutines.launch

class MovieListViewModel(private val showsRepository: ShowsRepository) : BaseViewModel() {
    private val _showsItemsResult: MutableLiveData<List<Show>> = MutableLiveData()
    val showsItemsResult: LiveData<List<Show>> = _showsItemsResult

    init {
        getShowsList()
    }

     fun getShowsList() {
        showLoading()
        viewModelScope.launch {
            showsRepository.getPopularTvShowsList().checkResponse(
                this@MovieListViewModel,
                onSuccessAction = { list ->
                    _showsItemsResult.value = list?.results
                }
            )
        }
    }
}
