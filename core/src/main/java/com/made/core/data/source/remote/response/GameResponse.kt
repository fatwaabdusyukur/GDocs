package com.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("results")
    val games : List<Games>
)
