package com.example.futurama.data.dto

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("head-shot")
    val headshot: String,
    val main: String
)