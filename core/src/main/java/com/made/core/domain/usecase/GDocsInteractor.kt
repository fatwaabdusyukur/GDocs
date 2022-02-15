package com.made.core.domain.usecase

import com.made.core.data.Resource
import com.made.core.domain.model.Game
import com.made.core.domain.repository.IGDocsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GDocsInteractor(private val repository: IGDocsRepository) : GDocsUseCase {
    override fun getAllGame(): Flow<Resource<List<Game>>> = repository.getAllGame()

    override fun getGameById(id: Int): Flow<Resource<Game>> = repository.getGameById(id)

    override fun getFavoriteGame(): Flow<List<Game>> = repository.getFavoriteGame()

    override fun setFavoriteGame(game: Game, scope: CoroutineScope) = repository.setFavoriteGame(game, scope = scope)
}