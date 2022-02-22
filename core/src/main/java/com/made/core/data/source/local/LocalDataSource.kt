package com.made.core.data.source.local

import com.made.core.data.source.local.entity.GameEntity
import com.made.core.data.source.local.room.GDocsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao : GDocsDao) {

    fun getAllGame() : Flow<List<GameEntity>> = dao.getAllGame()

    fun getGame(id : Int) : Flow<GameEntity> = dao.getGameById(id)

    fun getFavoriteGame() : Flow<List<GameEntity>> = dao.getFavoriteGame()

    fun getGamesByPlatform() : Flow<List<GameEntity>> = dao.getGamesByPlatform()

    suspend fun insertGame(games : GameEntity) = dao.insertGame(games)

    suspend fun updateGame(game : GameEntity) = dao.updateGame(game)

}