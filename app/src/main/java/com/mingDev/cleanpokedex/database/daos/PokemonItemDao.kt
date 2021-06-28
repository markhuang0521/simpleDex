package com.mingDev.cleanpokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mingDev.cleanpokedex.database.entity.AbilityDto

@Dao
interface PokemonItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAbilityList(abilityList: List<AbilityDto>)

    @Query("SELECT * FROM tb_abilities")
    fun getAllAbility(): List<AbilityDto>

    @Query("SELECT COUNT(*)  FROM tb_abilities  ")
    fun countAbilities(): Int
}