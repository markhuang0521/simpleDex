package com.mingDev.cleanpokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mingDev.cleanpokedex.utils.EvoTriggerEnum


@Entity(tableName = "tb_evolution")
data class EvolutionChainDto(
    @PrimaryKey
    val id: Int,
    val startingPokemon: String,
    var startingPokemonImageUrl: String? = null,
    val evo1Pokemon: String?,
    var evo1PokemonImageUrl: String? = null,

    val evo1Trigger: String?,
    val evo1Condition: String?,
    val evo2Pokemon: String?,
    var evo2PokemonImageUrl: String? = null,

    val evo2Condition: String?,
    val evo2Trigger: String?


) {
    fun formatEvo1Condition(): String {
        var result = "Not Available"
        if (!evo1Trigger.isNullOrEmpty() && !evo1Condition.isNullOrEmpty()) {
            if (evo1Trigger == EvoTriggerEnum.LevelUp.desc) {
                result = "Lv Up: $evo1Condition"
            } else {
                result = evo1Condition
            }
        }
        return result
    }

    fun formatEvo2Condition(): String {
        var result = "Not Available"
        if (!evo2Trigger.isNullOrEmpty() && !evo2Condition.isNullOrEmpty()) {
            if (evo1Trigger == EvoTriggerEnum.LevelUp.desc) {
                result = "Lv Up: $evo2Condition"
            } else {
                result = evo2Condition
            }
        }
        return result
    }
}