package com.example.futurama.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.futurama.data.repository.ICharactersRepository


class FuturamaViewModelFactory(private val icharactersRepository: ICharactersRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(icharactersRepository) as T
    }
}