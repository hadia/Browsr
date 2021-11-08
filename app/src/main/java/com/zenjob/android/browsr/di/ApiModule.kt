package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.data.TMDBApi
import com.zenjob.android.browsr.network.RetrofitClient
import org.koin.dsl.module

val apiModule = module {
    single { get<RetrofitClient>().create(TMDBApi::class.java) }
}
