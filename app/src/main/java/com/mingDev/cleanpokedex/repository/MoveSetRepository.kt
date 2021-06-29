package com.mingDev.cleanpokedex.repository

import com.mingDev.cleanpokedex.database.daos.MoveSetDao
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.network.PokeApiService
import com.mingDev.cleanpokedex.network.responses.ResultUrl
import com.mingDev.cleanpokedex.utils.toMoveSetDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class MoveSetRepository(
    private val pokeApi: PokeApiService,
    private val moveSetDao: MoveSetDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    // download data from api
    suspend fun downloadMoveSets() = withContext(ioDispatcher) {
        val apiResponse = pokeApi.getMoveSetList(100, 0)
        val results: List<ResultUrl> = apiResponse.results

        val moveSetDto = mutableListOf<MoveSetDto>()

        results.forEach {

            val move = pokeApi.getMoveByName(it.name)
            moveSetDto.add(move.toMoveSetDto())
        }
        moveSetDao.insertMoveSet(moveSetDto)
    }

    suspend fun getAllMoveSet(): List<MoveSetDto> = withContext(ioDispatcher) {
        return@withContext moveSetDao.getAllMoves()
    }

    suspend fun getPokemonListByMove(moveName: String): List<PokemonDto> =
        withContext(ioDispatcher) {

//            val move = getMoveByName(moveName)
//
//            val pokemons = getPokemonsByNameList(move.learnedPokemon)
//            Timber.d("pokemon list" + pokemons.toString())

            return@withContext moveSetDao.getPokemonsByMove(moveName)
        }

    suspend fun getMoveByName(moveName: String): MoveSetDto =
        withContext(ioDispatcher) {

            return@withContext moveSetDao.getMoveByName(moveName)
        }

    suspend fun getPokemonsByNameList(list: List<String>): List<PokemonDto> =
        withContext(ioDispatcher) {

            return@withContext moveSetDao.getPokemonsByNameList(list)
        }

    suspend fun countMoveSets(): Int = withContext(ioDispatcher) {
        return@withContext moveSetDao.countMoveSets()

    }

    suspend fun deleteAllMoveSet() = withContext(ioDispatcher) {
        moveSetDao.deleteAllMoveSet()
    }

}

