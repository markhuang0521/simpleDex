package com.mingDev.cleanpokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tb_items")
data class PokemonItemDto(
    @PrimaryKey
    val name: String,
    val id: Int,
    val detailEffect: String,
    val flavorText: String,
    val imageUrl: String

)
