package com.made.gdocs.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.core.domain.usecase.GDocsUseCase

class FavoriteViewModel(private val repository : GDocsUseCase) : ViewModel() {

    fun getFavoriteGames() = repository.getFavoriteGame().asLiveData()

}