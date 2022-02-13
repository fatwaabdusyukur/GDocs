package com.made.gdocs.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.core.domain.usecase.GDocsUseCase

class DetailViewModel(private val repository : GDocsUseCase) : ViewModel() {

    fun getGameById(id : Int) = repository.getGameById(id).asLiveData()

}