package com.made.core.data

import com.made.core.data.source.local.LocalDataSource
import com.made.core.data.source.remote.RemoteDataSource
import com.made.core.data.source.remote.network.ApiResponse
import com.made.core.data.source.remote.response.Games
import com.made.core.domain.model.Game
import com.made.core.domain.repository.IGDocsRepository
import com.made.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class GDocsRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IGDocsRepository {

    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<Games>>() {
            override fun loadFromDB(): Flow<List<Game>> =
                localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Game>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<Games>>> =
                remoteDataSource.getAllGame()

            override suspend fun saveCallResult(data: List<Games>) {
                val games = DataMapper.mapResponsesToEntities(data)
                games.forEach { localDataSource.insertGame(it) }
            }

        }.asFlow()

    override fun getGameById(id: Int): Flow<Game> =
        localDataSource.getGame(id).map {
            DataMapper.mapEntityToDomain(it)
        }

    override fun getFavoriteGame(): Flow<List<Game>> =
        localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteGame(game: Game, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            val entity = DataMapper.mapDomainToEntity(game)
            localDataSource.updateGame(entity)
        }
    }


}