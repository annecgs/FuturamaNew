package com.example.futurama.utils

import com.example.futurama.data.repository.CharactersRepository
import com.example.futurama.network.IFuturamaClient
import com.example.futurama.network.RetrofitInstance
import com.example.futurama.ui.home.FuturamaViewModelFactory


class ConfigViewModel {
    companion object {
        fun getFuturamaViewModelFactory(): FuturamaViewModelFactory {
            val charactersClient: IFuturamaClient by lazy {
                RetrofitInstance.get().create(IFuturamaClient::class.java)
            }
            val charactersRepository = CharactersRepository(charactersClient)
            return FuturamaViewModelFactory(charactersRepository)
        }
    }
}