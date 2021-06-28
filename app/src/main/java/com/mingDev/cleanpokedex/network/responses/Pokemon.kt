package com.mingDev.cleanpokedex.network.responses

data class Pokemon(
    val is_hidden: Boolean,
    val pokemon: PokemonUrl,
    val slot: Int
)