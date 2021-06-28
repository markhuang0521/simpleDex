package com.mingDev.cleanpokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tb_species")
data class PokemonDetailDto(
    @PrimaryKey
    val name: String,
    val genera: String,
    val flavorText: String,
    val moveSet: List<String>,
    val eggGroups: List<String>,
    val evoIndex: Int? = null,
    val baseHappiness: Int,
    val baseExperience: Int,
    val captureRate: Int,
    val growthRate: String,
    val hatchCounter: Int,
    val hasGenderDifferences: Boolean,
    val varieties: List<String>? = null
)