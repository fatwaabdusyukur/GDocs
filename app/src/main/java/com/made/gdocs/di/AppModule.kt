package com.made.gdocs.di

import com.made.core.domain.usecase.GDocsInteractor
import com.made.core.domain.usecase.GDocsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GDocsUseCase> { GDocsInteractor(get()) }
}