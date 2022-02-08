package com.made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.made.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GDocsDatabase : RoomDatabase() {

    abstract fun getGDocsDao() : GDocsDao

}