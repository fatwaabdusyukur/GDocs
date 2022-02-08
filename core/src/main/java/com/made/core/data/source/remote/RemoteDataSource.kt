package com.made.core.data.source.remote

import android.util.Log
import com.made.core.BuildConfig
import com.made.core.data.source.remote.network.ApiResponse
import com.made.core.data.source.remote.network.ApiServices
import com.made.core.data.source.remote.response.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiServices: ApiServices) {

    fun getAllGame() : Flow<ApiResponse<List<Games>>> =
        flow {
            try {
                val res = apiServices.getAllGame(BuildConfig.API_KEY)
                val dataArray = res.games
                if (dataArray.isNotEmpty()) emit(ApiResponse.Success(res.games))
                else emit(ApiResponse.Empty)
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

}