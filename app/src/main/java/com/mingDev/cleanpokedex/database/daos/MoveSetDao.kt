package com.mingDev.cleanpokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto


@Dao
interface MoveSetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoveSet(moveList: List<MoveSetDto>)

    @Query("SELECT * FROM tb_moveSets ORDER BY id")
    fun getAllMoves(): List<MoveSetDto>


    @Query("SELECT * FROM tb_moveSets where name in (:moveList) ORDER BY id")
    fun getMovesByList(moveList: List<String>): List<MoveSetDto>

    @Query("SELECT * FROM tb_pokemons where speciesName in (:pokemonList) ")
    fun getPokemonsByNameList(pokemonList: List<String>): List<PokemonDto>

    @Query(" SELECT * FROM tb_moveSets where name=:moveName LIMIT 1")
    fun getMoveByName(moveName: String): MoveSetDto


    fun getPokemonsByMove(moveName: String): List<PokemonDto> {
        val move = getMoveByName(moveName)
        return getPokemonsByNameList(move.learnedPokemon)
    }

    @Query("SELECT COUNT(*)  FROM tb_moveSets  ")
    fun countMoveSets(): Int

    @Query("Delete  FROM tb_moveSets")
    suspend fun deleteAllMoveSet()
}