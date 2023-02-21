package com.example.futurama.data.repository

import com.example.futurama.data.dto.FuturamaCharactersItem
import com.example.futurama.network.IFuturamaClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepository(private val futuramaClient: IFuturamaClient) : ICharactersRepository {
    override suspend fun getCharacters(): List<FuturamaCharactersItem> {
        return withContext(Dispatchers.IO) {
            futuramaClient.getCharacters().body()!!.characters
        }
    }
}