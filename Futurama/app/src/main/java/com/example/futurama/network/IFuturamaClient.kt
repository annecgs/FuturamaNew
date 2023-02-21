package com.example.futurama.network

import com.example.futurama.data.dto.FuturamaCharacters
import retrofit2.Response
import retrofit2.http.GET

interface IFuturamaClient {
    @GET("characters")
    suspend fun getCharacters(): Response<FuturamaCharacters>
}