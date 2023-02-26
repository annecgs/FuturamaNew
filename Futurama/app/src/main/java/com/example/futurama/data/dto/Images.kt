package com.example.futurama.data.dto

import kotlinx.serialization.SerialName


data class Images(
    @SerialName("head-shot")
    val headshot: String?,
    val main: String?
)