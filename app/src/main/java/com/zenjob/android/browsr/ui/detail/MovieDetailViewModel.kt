package com.zenjob.android.browsr.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zenjob.android.browsr.base.BaseViewModel
import com.zenjob.android.browsr.network.checkResponse
import com.zenjob.android.browsr.repository.ShowsRepository
import com.zenjob.android.browsr.utils.baseImgUrl
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val showsRepository: ShowsRepository) : BaseViewModel() {
    private val _showsImagesResult: MutableLiveData<List<String>> = MutableLiveData()
    val showsImagesResult: LiveData<List<String>> = _showsImagesResult


    fun fetchShowImages(movieId: Long) {
        showLoading()
        viewModelScope.launch {
            showsRepository.getMovieImages(movieId).checkResponse(
                this@MovieDetailViewModel,
                onSuccessAction = { list ->
                    list?.backdrops?.map {
                        "$baseImgUrl${it.file_path}"
                    }.also { _showsImagesResult.value = it }
                }
            )
        }
    }
}