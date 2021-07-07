package com.mingDev.cleanpokedex.network.responses

data class EvolutionDetail(
    val gender: String?,
    val held_item: String?,
    val item: Item?,
    val known_move: String?,
    val known_move_type: String?,
    val location: Location?,
    val min_affection: Int?,
    val min_beauty: Int?,
    val min_happiness: Int?,
    val min_level: Int?,
    val needs_overworld_rain: Boolean,
    val party_species: Any?,
    val party_type: Any?,
    val relative_physical_stats: Any?,
    val time_of_day: String?,
    val trade_species: Any?,
    val trigger: Trigger?,
    val turn_upside_down: Boolean?
)

data class Location(
    val name: String,
    val url: String
)
