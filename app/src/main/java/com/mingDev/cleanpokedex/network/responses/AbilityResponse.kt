package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AbilityResponse(
    val effect_entries: List<EffectEntry>,
    val flavor_text_entries: List<FlavorTextEntry>,
    val id: Int,
    val name: String,
    val pokemon: List<Pokemon>
)