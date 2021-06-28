package com.mingDev.cleanpokedex.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class PokemonDtoUpdate(
    val name: String,
    var isCaught: Boolean,
    var isImageShown: Boolean

)

@Entity(tableName = "tb_pokemons")
@Parcelize
data class PokemonDto(
    @PrimaryKey
    val name: String,
    val speciesName: String,
    val id: Int,
    val idString: String,
    val imageUrl: String? = null,
    var isImageShown: Boolean = true,
    val backUpImageUrl: String? = null,
    val types: List<String>,
    val height: Int,
    val weight: Int,
    val ability1: String,
    val ability2: String?,
    val hiddenAbility: String?,

    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val totalStat: Int,
    val generation: String,
    var isCaught: Boolean = false
) : Parcelable {


    fun toIdFormatString(): String {
        return String.format("#%03d", id)
    }

    fun maxProgress(): Int {
        val list = listOf(hp, attack, defense, specialAttack, specialDefense, speed)
        return list.max() ?: 0
    }

    fun heightFormatToString(): String {

        val heightInM = String.format("%.1f m", height.toFloat() / 10)
        val heightInFt = String.format("%.1f ft", (height.toFloat() / 10) * 3.28084)
        return "$heightInFt ($heightInM)"
    }

    fun weightFormatToString(): String {

        val weightInLb = String.format("%.1f lbs", weight.toFloat() / 10)
        val weightInKg = String.format(
            "%.1f Kg", (weight.toFloat() / 10) * 2.204622
        )
        return "$weightInLb ($weightInKg)"
    }

}