package com.made.gdocs.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.made.core.domain.model.Game
import com.made.core.domain.usecase.GDocsUseCase

class DetailViewModel(private val repository : GDocsUseCase) : ViewModel() {

    fun getGameById(id : Int) = repository.getGameById(id).asLiveData()

    fun setFavoriteGame(game : Game, newState : Boolean) {
        game.isFavorite = newState
        repository.setFavoriteGame(game, viewModelScope)
    }

}