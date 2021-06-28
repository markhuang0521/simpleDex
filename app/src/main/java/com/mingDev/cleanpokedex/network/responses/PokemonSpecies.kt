package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    val base_happiness: Int,
    val capture_rate: Int,
    val genera: List<Genus>,
    @Json(name = "generation") val generation: Generation,
    val id: Int,
    val name: String,
    @Json(name = "varieties") val varieties: List<Variety>,
    @Json(name = "habitat") val habitat: Habitat?,
    @Json(name = "egg_groups") val eggGroups: List<EggGroup>,
    @Json(name = "evolution_chain") val evolutionChain: EvolutionChain?,
    @Json(name = "evolves_from_species") val evolvesFrom_species: EvolvesFromSpecies?,
    @Json(name = "flavor_text_entries") val flavorTextEntries: List<FlavorText>,
    @Json(name = "forms_switchable") val formsSwitchable: Boolean,

    @Json(name = "growth_rate") val growthRate: GrowthRate,
    @Json(name = "has_gender_differences") val hasGenderDifferences: Boolean,
    @Json(name = "hatch_counter") val hatchCounter: Int,

    @Json(name = "is_baby") val isBaby: Boolean,
    @Json(name = "is_legendary") val isLegendary: Boolean,
    @Json(name = "is_mythical") val isMythical: Boolean

)

@JsonClass(generateAdapter = true)
class Genus(
    val genus: String,
    val language: Language
)

