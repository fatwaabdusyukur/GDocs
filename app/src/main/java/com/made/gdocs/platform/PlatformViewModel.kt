package com.made.gdocs.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.core.domain.usecase.GDocsUseCase

class PlatformViewModel(private val repository : GDocsUseCase) : ViewModel() {

    fun getGamesByPlatform(keyword : String) = repository.getGamesByPlatform(keyword).asLiveData()

}