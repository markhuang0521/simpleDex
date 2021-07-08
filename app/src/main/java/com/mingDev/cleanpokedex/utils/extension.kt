package com.mingDev.cleanpokedex.utils

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mingDev.cleanpokedex.database.entity.*
import com.mingDev.cleanpokedex.network.responses.*


const val STRING_ORDER_ASCENDING = "ascending"
const val STRING_ORDER_DESCENDING = "descending"

enum class EvoTriggerEnum(val desc: String) {
    LevelUp("level-up"),
    Trade("trade"),
    UerItem("use-item"),
    Shed("shed"),
    Other("other")
}


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

fun PokemonItemResponse.toItemDto(): PokemonItemDto {
    var detailedEffect = " Not Available"
    var shortEffect = " Not Available"

    if (this.effect_entries.isNotEmpty()) {
        val list = this.effect_entries.filter { it.language.name == "en" }
        shortEffect = list[0].short_effect
        detailedEffect = list[0].effect
    }
    val flavorText =
        this.flavor_text_entries.filter {
            it.language.name == "en"
        }[0].text
    return PokemonItemDto(
        this.name,
        this.id,
        shortEffect,
        detailedEffect,
        flavorText,
        this.sprites.default

    )

}

fun EvolutionResponse.toEvolutionDto(): EvolutionChainDto {
    val startPokemon = this.chain.species.name
    val evo1 = this.chain.evolves_to
    var evo1Pokemon: String? = null
    var evo1Trigger: String? = null
    var evo1Condition: String? = null
    var evo2Pokemon: String? = null
    var evo2Trigger: String? = null
    var evo2Condition: String? = null
    if (!evo1.isNullOrEmpty()) {
        val evo1Detail: EvolutionDetail? = evo1[0]?.evolution_details?.get(0)
        evo1Pokemon = evo1[0]?.species?.name
        evo1Trigger = evo1Detail?.trigger?.name
        if (!evo1Trigger.isNullOrEmpty()) {
            evo1Condition = when (evo1Trigger) {
                EvoTriggerEnum.LevelUp.desc -> evo1Detail?.min_level.toString()
                EvoTriggerEnum.Trade.desc -> "trade"
                EvoTriggerEnum.UerItem.desc -> evo1Detail?.item?.name
                EvoTriggerEnum.Other.desc -> "Not Available"
                EvoTriggerEnum.Shed.desc -> "shed"
                else -> null
            }
        }
        val evo2 = evo1[0]?.evolves_to

        if (!evo2.isNullOrEmpty()) {
            val evo2Detail: EvolutionDetail? = evo2[0].evolution_details?.get(0)
            evo2Pokemon = evo2[0]?.species?.name
            evo2Trigger = evo2Detail?.trigger?.name
            if (!evo2Trigger.isNullOrEmpty()) {
                evo2Condition = when (evo1Trigger) {
                    EvoTriggerEnum.LevelUp.desc -> evo2Detail?.min_level.toString()
                    EvoTriggerEnum.Trade.desc -> "trade"
                    EvoTriggerEnum.UerItem.desc -> evo2Detail?.item?.name
                    EvoTriggerEnum.Other.desc -> "Not Available"
                    EvoTriggerEnum.Shed.desc -> "shed"
                    else -> null
                }
            }
        }
    }




    return EvolutionChainDto(
        id = this.id,
        startingPokemon = startPokemon,
        evo1Pokemon = evo1Pokemon,
        evo1Trigger = evo1Trigger,
        evo1Condition = evo1Condition,
        evo2Pokemon = evo2Pokemon,
        evo2Condition = evo2Condition,
        evo2Trigger = evo2Trigger
    )
}






