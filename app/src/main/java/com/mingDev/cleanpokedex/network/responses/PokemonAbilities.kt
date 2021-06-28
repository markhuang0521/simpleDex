package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class PokemonAbilities(
    val ability: com.mingDev.cleanpokedex.network.responses.Ability,
    val is_hidden: Boolean,
    val slot: Int
)