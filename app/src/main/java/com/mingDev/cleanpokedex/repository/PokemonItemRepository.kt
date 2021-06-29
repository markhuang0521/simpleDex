package com.mingDev.cleanpokedex.repository

import com.mingDev.cleanpokedex.database.daos.PokemonItemDao
import com.mingDev.cleanpokedex.database.entity.PokemonItemDto
import com.mingDev.cleanpokedex.network.PokeApiService
import com.mingDev.cleanpokedex.network.responses.ResultUrl
import com.mingDev.cleanpokedex.utils.toItemDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonItemRepository(
    private val pokeApi: PokeApiService,
    private val itemDao: PokemonItemDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    // download data from api
    suspend fun downloadItems() = withContext(ioDispatcher) {
        val apiResponse = pokeApi.getItemList(100, 0)
        val results: List<ResultUrl> = apiResponse.results

        val itemList = mutableListOf<PokemonItemDto>()

        results.forEach {

            val ability = pokeApi.getItemByName(it.name)
            itemList.add(ability.toItemDto())
        }
        itemDao.insertItemList(itemList)
    }

    suspend fun getAllItems(): List<PokemonItemDto> = withContext(ioDispatcher) {
        return@withContext itemDao.getAllItems()
    }


    suspend fun countItems(): Int = withContext(ioDispatcher) {
        return@withContext itemDao.countItems()

    }

}