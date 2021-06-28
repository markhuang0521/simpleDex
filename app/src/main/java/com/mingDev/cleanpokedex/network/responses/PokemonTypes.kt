package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class PokemonTypes(
    val slot: Int,
    val type: com.mingDev.cleanpokedex.network.responses.Type
)