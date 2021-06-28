package com.mingDev.cleanpokedex.repository

import com.mingDev.cleanpokedex.database.daos.PokemonItemDao
import com.mingDev.cleanpokedex.network.PokeApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PokemonItemRepository(
    private val pokeApi: PokeApiService,
    private val itemDao: PokemonItemDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    
}