package com.example.futurama.ui.home

import com.example.futurama.data.repository.CharactersRepository
import com.example.futurama.network.IFuturamaClient
import com.example.futurama.network.RetrofitInstance

class ConfigViewModel {
    companion object {
        fun getMainViewModelFactory(): HomeViewModelFactory {
            val futuramaClient: IFuturamaClient by lazy {
                RetrofitInstance.get().create(IFuturamaClient::class.java)
            }
            val futuramaRepository = CharactersRepository(futuramaClient)
            return HomeViewModelFactory(futuramaRepository)
        }
    }
}