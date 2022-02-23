package com.made.gdocs.di

import com.made.core.domain.usecase.GDocsInteractor
import com.made.core.domain.usecase.GDocsUseCase
import com.made.gdocs.detail.DetailViewModel
import com.made.gdocs.home.HomeViewModel
import com.made.gdocs.platform.PlatformViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GDocsUseCase> { GDocsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { PlatformViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}