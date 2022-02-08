package com.made.core.data.source.local.room

import androidx.room.*
import com.made.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GDocsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(vararg game : GameEntity)

    @Update
    suspend fun updateGame(game : GameEntity)

    @Query("SELECT * FROM games")
    fun getAllGame() : Flow<List<GameEntity>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGameById(id : Int) : Flow<GameEntity>

    @Query("SELECT * FROM games WHERE isFavorite = 1")
    fun getFavoriteGame() : Flow<List<GameEntity>>

}