package com.mingDev.cleanpokedex.utils

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mingDev.cleanpokedex.database.entity.AbilityDto
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDetailDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.network.responses.AbilityResponse
import com.mingDev.cleanpokedex.network.responses.MoveSetResponse
import com.mingDev.cleanpokedex.network.responses.PokemonInfo
import com.mingDev.cleanpokedex.network.responses.PokemonSpecies


const val STRING_ORDER_ASCENDING = "ascending"
const val STRING_ORDER_DESCENDING = "descending"


fun Fragment.setToolbarTitle(title: String = "") {
    if (activity is AppCompatActivity) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }
}

fun Fragment.showTitleBar(isShow: Boolean) {
    if (activity is AppCompatActivity) {
        if (isShow) {
            (activity as AppCompatActivity).supportActionBar?.show()
        } else {
            (activity as AppCompatActivity).supportActionBar?.hide()

        }
    }
}

fun Fragment.setToolbarColor(color: Int) {
    if (activity is AppCompatActivity) {
        val colorDrawable = ColorDrawable(color)
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(colorDrawable)
    }
}

fun Fragment.setDisplayHomeAsUpEnabled(bool: Boolean) {
    if (activity is AppCompatActivity) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            bool
        )
    }
}


@SuppressLint("DefaultLocale")
fun PokemonInfo.toPokemonDto(pokemonSpecies: PokemonSpecies): PokemonDto {

    val idString = id.toString()

//    val imageUrl = pokemonInfo.sprites.otherImage.officialArtwork.front_default
//    val backUpImageUrl = pokemonInfo.sprites.front_default

//direct image url from the json response
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    val backUpImageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"

    // property from pokemon info
    val types: List<String> = this.types.map { it.type.name }
    var hiddenAbility: String? = null
    var ability2: String? = null
    var ability1 = ""
    this.abilities.forEach {

        if (it.slot == 1) {
            ability1 = it.ability.name

        }
        if (it.slot == 3) {
            hiddenAbility = it.ability.name
        }
        if (it.slot == 2) {
            ability2 = it.ability.name
        }
    }
//    val type1: String = this.types[0].type.name
//    val type2: String? = if (this.types.size > 1) this.types[1].type.name else null

    val hp: Int = this.stats[0].base_stat
    val attack: Int = this.stats[1].base_stat
    val defense: Int = this.stats[2].base_stat
    val specialAttack: Int = this.stats[3].base_stat
    val specialDefense: Int = this.stats[4].base_stat
    val speed: Int = this.stats[5].base_stat
    val totalStat = hp + attack + defense + specialAttack + specialDefense + speed

    // properties from pokemon species
    val generation = pokemonSpecies.generation.name

    return PokemonDto(
        this.name.capitalize(),
        this.species.name,
        id,
        idString,
        imageUrl,
        true,
        backUpImageUrl,
        types,
        this.height,
        this.weight,
        ability1,
        ability2,
        hiddenAbility,
        hp,
        attack,
        defense,
        specialAttack,
        specialDefense,
        speed,
        totalStat,
        generation
    )


}


fun PokemonSpecies.toDetailDto(pokemonInfo: PokemonInfo): PokemonDetailDto {
    val flavorText = this.flavorTextEntries.filter { it.language.name == "en" }
    val genus = this.genera.filter { it.language.name == "en" }
    val eggGroups = this.eggGroups.map { it.name }
    val moveSet = pokemonInfo.moves.map { it.move.name }
//    val eggGroup2: String? = if (this.eggGroups.size > 1) eggGroups[1].name else null
    val evoIndex: Int? = this.evolutionChain?.url?.split("/")?.dropLast(1)?.last()?.toInt()
    val varieties = this.varieties.filter { !it.is_default }.map { it.pokemon.name }

    return PokemonDetailDto(
        this.name,
        genus[0].genus,
        flavorText[0].flavor_text,
        moveSet,
        eggGroups,
        evoIndex,
        this.base_happiness,
        pokemonInfo.base_experience,
        this.capture_rate,
        this.growthRate.name,
        this.hatchCounter,
        this.hasGenderDifferences,
        varieties
    )

}

fun MoveSetResponse.toMoveSetDto(): MoveSetDto {
    var shortEffect = " Not Available"
    var detailedEffect = " Not Available"
    if (this.effect_entries.isNotEmpty()) {
        shortEffect = this.effect_entries[0].short_effect
        detailedEffect = this.effect_entries[0].effect
    }
    val power = this.power ?: 0
    val accuracy = this.accuracy ?: 0


    val pokemonLearnedList = this.learned_by_pokemon.map { it.name }


    return MoveSetDto(
        this.name,
        this.id,
        this.type.name,
        power,
        this.pp,
        accuracy,
        this.contest_type?.name,
        this.damage_class.name,
        this.effect_chance,
        shortEffect,
        detailedEffect,
        "TBD",
        pokemonLearnedList
    )
}

fun AbilityResponse.toAbilityDto(): AbilityDto {
    var shortEffect = " Not Available"
    var detailedEffect = " Not Available"
    if (this.effect_entries.isNotEmpty()) {
        val list = this.effect_entries.filter { it.language.name == "en" }
        shortEffect = list[0].short_effect
        detailedEffect = list[0].effect
    }
    val flavorText =
        this.flavor_text_entries.filter {
            it.language.name == "en" && it.version_group.name == "sun-moon"
        }[0].flavor_text

    val pokemonLearnedList = this.pokemon.map { it.pokemon.name }

    return AbilityDto(
        this.name,
        this.id,
        shortEffect,
        detailedEffect,
        flavorText,
        pokemonLearnedList

    )
}





