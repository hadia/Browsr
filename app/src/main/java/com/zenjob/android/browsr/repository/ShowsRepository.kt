package com.zenjob.android.browsr.repository

import com.zenjob.android.browsr.base.BaseRepository
import com.zenjob.android.browsr.data.MovieImagesList
import com.zenjob.android.browsr.data.PaginatedListResponse
import com.zenjob.android.browsr.data.Show
import com.zenjob.android.browsr.data.TMDBApi
import com.zenjob.android.browsr.network.Resource

class ShowsRepository(private val tMDBEndPointsEndPoint: TMDBApi) : BaseRepository() {

    suspend fun getPopularTvShowsList(currentPage: Int=1): Resource<PaginatedListResponse<Show>> {
        return getResult { tMDBEndPointsEndPoint.getPopularTvShows(page=currentPage) }
    }

    suspend fun getMovieImages(movieId:Long): Resource<MovieImagesList> {
        return getResult { tMDBEndPointsEndPoint.getMovieImages(movieId) }
    }
}
