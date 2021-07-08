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
        var moves = moveSetDao.getAllMoves()

        if (moves.isEmpty()) {

            downloadMoveSets()
            moves = moveSetDao.getAllMoves()
        }
        return@withContext moves
    }

    suspend fun getSelectedMoves(moveList: List<String>): List<MoveSetDto> =
        withContext(ioDispatcher) {

            return@withContext moveSetDao.getMovesByList(moveList)
        }

    suspend fun getPokemonListByMove(moveName: String): List<PokemonDto> =
        withContext(ioDispatcher) {


            return@withContext moveSetDao.getPokemonsByMove(moveName)
        }

    suspend fun searchMovesByName(moveName: String): List<MoveSetDto> =
        withContext(ioDispatcher) {

            return@withContext moveSetDao.searchMovesByName(moveName)
        }


    suspend fun countMoveSets(): Int = withContext(ioDispatcher) {
        return@withContext moveSetDao.countMoveSets()

    }


}

