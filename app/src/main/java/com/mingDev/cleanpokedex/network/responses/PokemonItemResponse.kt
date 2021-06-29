package com.mingDev.cleanpokedex.network.responses

data class PokemonItemResponse(
    val category: Category,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntryForItem>,
    val id: Int,
    val name: String,
    val sprites: Sprites
)

data class FlavorTextEntryForItem(
    val text: String,
    val language: Language,
    val version_group: VersionGroup
)