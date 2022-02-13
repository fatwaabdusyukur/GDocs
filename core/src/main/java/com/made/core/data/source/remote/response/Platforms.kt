package com.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Platforms(
    @SerializedName("platform")
    val platform : Platform
)

data class Platform(
    @SerializedName("name")
    val name : String
    )
