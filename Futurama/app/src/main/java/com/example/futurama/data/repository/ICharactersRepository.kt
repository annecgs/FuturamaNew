package com.example.futurama.data.repository

import com.example.futurama.data.dto.FuturamaCharactersItem

interface ICharactersRepository {
    suspend fun getCharacters(): List<FuturamaCharactersItem>
}