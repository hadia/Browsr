package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.BuildConfig
import com.zenjob.android.browsr.network.HeadersInterceptor
import com.zenjob.android.browsr.network.RetrofitClient
import org.koin.dsl.module

val networkModule = module {
    single {
        RetrofitClient.Builder(BuildConfig.BASE_URL)
            .build {
                useDefaultLoggerInterceptor()
                addInterceptor(HeadersInterceptor())
            }
    }
}
