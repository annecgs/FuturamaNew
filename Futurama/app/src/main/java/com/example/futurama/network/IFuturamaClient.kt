package com.example.futurama.network

import com.example.futurama.data.dto.FuturamaCharacters
import com.example.futurama.data.dto.FuturamaCharactersItem
import retrofit2.Response
import retrofit2.http.GET

interface IFuturamaClient {
    @GET("characters")
    //suspend fun getFuturamaCharacters(): Response<FuturamaCharacters>
    suspend fun getFuturamaCharacters(): List<FuturamaCharactersItem>
}