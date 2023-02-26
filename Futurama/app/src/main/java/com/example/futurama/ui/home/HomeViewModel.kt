package com.example.futurama.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurama.data.dto.FuturamaApiResult
import com.example.futurama.data.dto.FuturamaCharactersItem
import com.example.futurama.data.repository.ICharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeViewModel(private val icharactersRepository: ICharactersRepository) : ViewModel() {

    private val _charactersItem = MutableLiveData<FuturamaApiResult<List<FuturamaCharactersItem>>>()
    val characterItem: LiveData<FuturamaApiResult<List<FuturamaCharactersItem>>> = _charactersItem

    var charactersFromApi: List<FuturamaCharactersItem> = ArrayList()

    fun setCharacter(character: Int) {
        viewModelScope.launch {
            _charactersItem.value = FuturamaApiResult.Loading()
            try {
                if (charactersFromApi.isNullOrEmpty()) {
                    charactersFromApi = withContext(Dispatchers.IO) {
                        icharactersRepository.getCharacter()
                    }
                }
                //_pokemonSelected.value = pokemonsFromApi.find { it.id == pokemonId }
                _charactersItem.value = FuturamaApiResult.Success(charactersFromApi)
            } catch (e: Exception) {
                val characterResult = FuturamaApiResult.Error<List<FuturamaCharactersItem>>(e)
                _charactersItem.value = characterResult
            }
        }
    }

    fun getCharactersFromRetrofit() {
        viewModelScope.launch {
            _charactersItem.value = FuturamaApiResult.Loading()
            try {
                if (charactersFromApi.isNullOrEmpty()) {
                    charactersFromApi = withContext(Dispatchers.IO) {
                        icharactersRepository.getCharacter()
                    }
                }
                _charactersItem.value = FuturamaApiResult.Success(charactersFromApi)
            } catch (e: Exception) {
                val characterResult = FuturamaApiResult.Error<List<FuturamaCharactersItem>>(e)
                Log.d("FuturamaResult", "Result: ${characterResult.throwable.message}")
                _charactersItem.value = characterResult
            }
        }
    }
}