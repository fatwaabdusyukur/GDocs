package com.made.core.domain.repository

import com.made.core.data.Resource
import com.made.core.domain.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface IGDocsRepository {

    fun getAllGame() : Flow<Resource<List<Game>>>

    fun getGameById(id : Int) : Flow<Resource<Game>>

    fun getFavoriteGame() : Flow<List<Game>>

    fun setFavoriteGame(game: Game, scope: CoroutineScope)

}