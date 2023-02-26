package com.example.futurama.data.dto

data class FuturamaCharactersItem(
    val age: String?,
    val gender: String?,
    val homePlanet: String?,
    val id: Int?,
    val images: Images,
    val name: Name,
    val occupation: String?,
    val sayings: List<String>,
    val species: String?
)