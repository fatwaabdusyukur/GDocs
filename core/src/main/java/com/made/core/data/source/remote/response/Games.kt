package com.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("released")
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("metacritic")
    val metacritic: Int?,
    @SerializedName("platforms")
    val platforms: List<Platforms>?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("publishers")
    val publishers: List<Publisher>,
    @SerializedName("developers")
    val developers: List<Developer>,
    @SerializedName("description_raw")
    val descriptionRaw: String
)
