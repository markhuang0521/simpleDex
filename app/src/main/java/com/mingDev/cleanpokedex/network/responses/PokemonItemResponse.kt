package com.mingDev.cleanpokedex.network.responses

data class PokemonItemResponse(
    val category: Category,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val id: Int,
    val name: String,
    val sprites: Sprites
)