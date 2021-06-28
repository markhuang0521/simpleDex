package com.mingDev.cleanpokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tb_moveSets")
data class MoveSetDto(
    @PrimaryKey
    val name: String,
    val id: Int,
    val type: String,
    val power: Int,
    val pp: Int,
    val accuracy: Int,
    val contestType: String?,
    val damageClass: String,
    val effectChance: Int?,
    val shortEffect: String,
    val detailEffect: String,
    val flavorText: String = "TBD",
    val learnedPokemon: List<String>

)


