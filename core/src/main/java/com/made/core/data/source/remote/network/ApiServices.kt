package com.made.core.data.source.remote.network

import com.made.core.data.source.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("games")
    suspend fun getAllGame( @Query("key") key : String ) : GameResponse

}