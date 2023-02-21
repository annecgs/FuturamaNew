package com.example.futurama.data.dto

sealed class FuturamaApiResult <T> {
    class Loading<T> : FuturamaApiResult<T>()
    class Success<T>(val data: T) : FuturamaApiResult<T>()
    class Error<T>(val throwable: Throwable) : FuturamaApiResult<T>()
}