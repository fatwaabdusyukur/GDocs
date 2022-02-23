package com.made.gdocs.favorite

import com.made.gdocs.favorite.ui.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favModule = module {
    viewModel { FavoriteViewModel(get()) }
}