package com.zenjob.android.browsr.data

data class MovieImagesList(
    val backdrops: List<Backdrop>,
    val id: Int,
)

data class Backdrop(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: String,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)