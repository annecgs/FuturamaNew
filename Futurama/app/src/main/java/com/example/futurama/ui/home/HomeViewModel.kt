package com.example.futurama.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Rio de Janeiro \n 18 de fevereiro de 2023 15:06"
    }
    val text: LiveData<String> = _text
}