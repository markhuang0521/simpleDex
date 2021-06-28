package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Species(
    val name: String,
    val url: String
)