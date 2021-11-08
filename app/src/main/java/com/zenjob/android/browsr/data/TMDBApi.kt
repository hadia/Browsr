package com.zenjob.android.browsr.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    suspend fun getPopularTvShows(
        @Query("language") query: String= "en",
        @Query("page") page: Int? = null
    ): Response<PaginatedListResponse<Show>>

    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movieId: Long,
        @Query("language") query: String? = null
    ): Response<Show>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Long,
        @Query("include_image_language") language: String = "en"
    ): Response<MovieImagesList>
}
