package com.mingDev.cleanpokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mingDev.cleanpokedex.database.entity.AbilityDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto


@Dao
interface AbilityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAbilityList(abilityList: List<AbilityDto>)

    @Query("SELECT * FROM tb_abilities")
    fun getAllAbility(): List<AbilityDto>

    @Query("SELECT * FROM tb_abilities where name=:abilityName LIMIT 1")
    fun getAbilityByName(abilityName: String): AbilityDto

    @Query("SELECT * FROM tb_pokemons where speciesName in (:pokemonList) ")
    fun getPokemonsByNameList(pokemonList: List<String>): List<PokemonDto>


    fun getPokemonsByAbility(abilityName: String): List<PokemonDto> {
        val ability = getAbilityByName(abilityName)
        return getPokemonsByNameList(ability.pokemonWithAbility)
    }

    @Query("SELECT COUNT(*)  FROM tb_abilities  ")
    fun countAbilities(): Int

}