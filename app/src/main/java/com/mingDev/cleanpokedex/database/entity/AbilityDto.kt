package com.mingDev.cleanpokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tb_abilities")
class AbilityDto(
    @PrimaryKey
    val name: String,
    val id: Int,
    val shortEffect: String,
    val detailEffect: String,
    val flavorText: String = "TBD",
    val pokemonWithAbility: List<String>

)