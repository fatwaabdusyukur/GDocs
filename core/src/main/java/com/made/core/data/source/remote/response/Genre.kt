package com.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name : String
)
