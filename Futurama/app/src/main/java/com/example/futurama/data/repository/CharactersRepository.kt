package com.example.futurama.data.repository

import com.example.futurama.data.dto.FuturamaCharactersItem
import com.example.futurama.network.IFuturamaClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharactersRepository(private val futuramaClient: IFuturamaClient) : ICharactersRepository {
    override suspend fun getCharacter(): List<FuturamaCharactersItem> {
        return withContext(Dispatchers.IO) {
            /* var list = ArrayList<FuturamaCharactersItem>()
            var listResult: Array<FuturamaCharactersItem> = Gson().fromJson(
                futuramaClient.getFuturamaCharacters().body().toString(),
                Array<com.example.futurama.data.dto.FuturamaCharactersItem>::class.java
            )
            listResult
            for (i in listResult){
                Log.i("item",i.name.first.toString())
            }

            listResult.asList()
        }*/
            futuramaClient.getFuturamaCharacters()
        }
    }

}