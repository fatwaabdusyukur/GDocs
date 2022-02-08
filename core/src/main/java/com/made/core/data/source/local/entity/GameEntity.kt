package com.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "description")
    val description : String,

    @ColumnInfo(name = "released")
    val released : String,

    @ColumnInfo(name = "image")
    val image : String?,

    @ColumnInfo(name = "metaScore")
    val metaScore : Int,

    @ColumnInfo(name = "platforms")
    val platforms : String,

    @ColumnInfo(name = "genres")
    val genres : String,

    @ColumnInfo(name = "publisher")
    val publisher: String,

    @ColumnInfo(name = "developers")
    val developer: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)
