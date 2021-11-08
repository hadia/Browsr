package com.zenjob.android.browsr.di

import com.zenjob.android.browsr.repository.ShowsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { ShowsRepository(get()) }
}
