package com.mingDev.cleanpokedex.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfo(
    val weight: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val species: Species,
    val location_area_encounters: String,
    val base_experience: Int,

    @Json(name = "abilities") val abilities: List<PokemonAbilities>,
    @Json(name = "forms") val forms: List<Form>,
    @Json(name = "moves") val moves: List<PokemonMove>,
    @Json(name = "stats") val stats: List<PokemonStats>,
    @Json(name = "types") val types: List<PokemonTypes>
//    @Json(name = "sprites") val sprites: PokemonSprites

) {
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)

}