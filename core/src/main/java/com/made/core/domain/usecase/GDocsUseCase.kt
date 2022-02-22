package com.made.core.domain.usecase

import com.made.core.data.Resource
import com.made.core.domain.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GDocsUseCase {

    fun getAllGame() : Flow<Resource<List<Game>>>

    fun getGameById(id : Int) : Flow<Resource<Game>>

    fun getGamesByPlatform(keyword : String) : Flow<List<Game>>

    fun getFavoriteGame() : Flow<List<Game>>

    fun setFavoriteGame(game: Game, scope: CoroutineScope)

}