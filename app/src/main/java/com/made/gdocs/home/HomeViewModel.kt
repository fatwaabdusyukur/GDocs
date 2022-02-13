package com.made.gdocs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.core.domain.usecase.GDocsUseCase

class HomeViewModel(private val repository : GDocsUseCase) : ViewModel() {

    fun getAllGame() = repository.getAllGame().asLiveData()

}