package com.mingDev.cleanpokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mingDev.cleanpokedex.database.entity.PokemonItemDto

@Dao
interface PokemonItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItemList(itemList: List<PokemonItemDto>)

    @Query("SELECT * FROM tb_items")
    fun getAllItems(): List<PokemonItemDto>

    @Query("SELECT COUNT(*)  FROM tb_items  ")
    fun countItems(): Int

//    @Query("SELECT * FROM tb_items ")
//
//    fun getItemByName(abilityName: String): PokemonItemDto
}