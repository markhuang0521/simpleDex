package com.mingDev.cleanpokedex.network.responses

data class MoveSetResponse(
    val accuracy: Int?,

    val contest_type: ContestType?,
    val damage_class: DamageClass,
    val effect_chance: Int?,
    val type: Type,
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val id: Int,
    val learned_by_pokemon: List<LearnedByPokemon>,
    val name: String,
    val power: Int?,
    val pp: Int


)