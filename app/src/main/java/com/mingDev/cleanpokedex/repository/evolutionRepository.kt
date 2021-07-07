package com.mingDev.cleanpokedex.repository

import com.mingDev.cleanpokedex.database.daos.PokeDexDao
import com.mingDev.cleanpokedex.database.entity.EvolutionChainDto
import com.mingDev.cleanpokedex.network.PokeApiService
import com.mingDev.cleanpokedex.network.responses.EvolutionResponse
import com.mingDev.cleanpokedex.utils.toEvolutionDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class evolutionRepository(
    private val pokeApi: PokeApiService,
    private val pokeDexDao: PokeDexDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {




}