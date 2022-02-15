package com.made.core.domain.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Game(
    val id : Int,
    val name : String,
    val description : String,
    val released : String,
    val bgImage : String,
    val metascore : Int,
    val platforms : String,
    val publisher : String,
    val developers : String,
    val genres : String,
    var isFavorite : Boolean
) : Parcelable
