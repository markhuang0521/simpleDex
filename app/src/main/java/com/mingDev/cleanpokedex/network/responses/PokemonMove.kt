package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class PokemonMove(
    val move: com.mingDev.cleanpokedex.network.responses.Move
)