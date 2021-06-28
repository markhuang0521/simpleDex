package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class PokemonStats(
    val base_stat: Int,
    val effort: Int,
    val stat: com.mingDev.cleanpokedex.network.responses.Stat
)