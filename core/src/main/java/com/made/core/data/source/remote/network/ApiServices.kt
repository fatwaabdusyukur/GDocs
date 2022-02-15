package com.made.core.data.source.remote.network

import com.made.core.data.source.remote.response.GameResponse
import com.made.core.data.source.remote.response.Games
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("games")
    suspend fun getAllGame( @Query("key") key : String ) : GameResponse

    @GET("games/{id}")
    suspend fun getGameById( @Path("id") id : Int, @Query("key") key : String ) : Games

}