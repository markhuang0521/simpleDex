package com.mingDev.cleanpokedex.repository

import com.mingDev.cleanpokedex.database.*
import com.mingDev.cleanpokedex.database.daos.PokeDexDao
import com.mingDev.cleanpokedex.database.entity.EvolutionChainDto
import com.mingDev.cleanpokedex.database.entity.PokemonDetailDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.database.entity.PokemonDtoUpdate
import com.mingDev.cleanpokedex.network.PokeApiService
import com.mingDev.cleanpokedex.network.responses.EvolutionResponse
import com.mingDev.cleanpokedex.network.responses.ResultUrl
import com.mingDev.cleanpokedex.utils.toDetailDto
import com.mingDev.cleanpokedex.utils.toEvolutionDto
import com.mingDev.cleanpokedex.utils.toPokemonDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PokemonRepository(
    private val pokeApi: PokeApiService,
    private val pokeDexDao: PokeDexDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    // fetch data from api

    suspend fun downloadPokedex() = withContext(ioDispatcher) {
        val response = pokeApi.getPokemonList(100, 0)
        val results: List<ResultUrl> = response.results
        val pokemonDtos = mutableListOf<PokemonDto>()
        val pokemonDetailDtos = mutableListOf<PokemonDetailDto>()

        results.forEach {

            val pokeInfo = pokeApi.getPokemonByName(it.name)
            val pokeSpecies = pokeApi.getPokemonSpeciesByName(pokeInfo.species.name)
            val pokeDto = pokeInfo.toPokemonDto(pokeSpecies)
            val detailDto = pokeSpecies.toDetailDto(pokeInfo)
            pokemonDtos.add(pokeDto)
            pokemonDetailDtos.add(detailDto)

        }

        pokeDexDao.insertPokemons(pokemonDtos)
        pokeDexDao.insertPokemonSpecies(pokemonDetailDtos)

    }

//    suspend fun getPokemonDetail(pokemonName: String): PokemonDetailDto =
//        withContext(ioDispatcher) {
//            var pokemonDetail = pokeDexDao.getPokemonDetailByName(pokemonName)
//
//            if (pokemonDetail == null) {
//                val pokeInfo = pokeApi.getPokemonByName(pokemonName)
//                pokemonDetail = pokeApi.getPokemonSpeciesByName(pokemonName).toDetailDto(pokeInfo)
//            }
//            return@withContext pokemonDetail
//
//        }

    // fetch data from tb_pokemon
    suspend fun getAllPokemons(): List<PokemonDto> = withContext(ioDispatcher) {
        return@withContext pokeDexDao.getAllPokemons()

    }

    suspend fun getCaughtPokemons(): List<PokemonDto> = withContext(ioDispatcher) {
        return@withContext pokeDexDao.getCaughtPokemons()

    }

    suspend fun getPokemonById(id: Int): PokemonDto = withContext(ioDispatcher) {
        return@withContext pokeDexDao.getPokemonById(id)

    }

    suspend fun getPokemonByName(name: String): PokemonDto = withContext(ioDispatcher) {
        return@withContext pokeDexDao.getPokemonByName(name)

    }

    suspend fun getPokemonDetailByName(name: String): PokemonDetailDto =
        withContext(ioDispatcher) {
            return@withContext pokeDexDao.getPokemonDetailByName(name)
        }

    suspend fun countPokemons(): Int = withContext(ioDispatcher) {
        return@withContext pokeDexDao.countPokemons()

    }

    suspend fun deleteAllPokemon() = withContext(ioDispatcher) {
        pokeDexDao.deleteAllPokemons()
    }

    suspend fun updatePokemon(pokemonDtoIsCaught: PokemonDtoUpdate) =
        withContext(ioDispatcher) {
            pokeDexDao.updatePokemon(pokemonDtoIsCaught)
        }

    suspend fun searchPokemonsByName(name: String): List<PokemonDto> = withContext(ioDispatcher) {
        return@withContext pokeDexDao.searchPokemonsByName(name)
    }

    suspend fun filterPokemonsByType(type: String): List<PokemonDto> = withContext(ioDispatcher) {
        return@withContext pokeDexDao.filterPokemonsByType(type)
    }

    suspend fun filterPokemonsByGen(generation: String): List<PokemonDto> =
        withContext(ioDispatcher) {
            return@withContext pokeDexDao.filterPokemonsByGen(generation)
        }

    suspend fun getPokemonSortBy(sort: String, order: String): List<PokemonDto> =
        withContext(ioDispatcher) {

            return@withContext pokeDexDao.getPokemonSortBy(sort, order)
        }

    suspend fun getSelectedPokemons(pokemonList: List<String>): List<PokemonDto> =
        withContext(ioDispatcher) {

            return@withContext pokeDexDao.getPokemonsByNameListOrderById(pokemonList)
        }

    // get moves from
    suspend fun getMovesByList(list: List<String>) = withContext(ioDispatcher) {
        return@withContext pokeDexDao.getMovesByList(list)
    }

    suspend fun getPokemonImageByName(name: String?): String? = withContext(ioDispatcher) {
        return@withContext pokeDexDao.getPokemonImageByName(name)
    }

    suspend fun downloadEvolutionChainById(id: Int) = withContext(ioDispatcher) {
        val evolutionResponse: EvolutionResponse = pokeApi.getEvolutionChainById(id)
        val evolutionChainDto = evolutionResponse.toEvolutionDto()
        val image1 = getPokemonImageByName(evolutionChainDto.startingPokemon)
        Timber.d("start image " + image1)
        evolutionChainDto.startingPokemonImageUrl = image1

        evolutionChainDto.evo1PokemonImageUrl = getPokemonImageByName(evolutionChainDto.evo1Pokemon)

        evolutionChainDto.evo2PokemonImageUrl = getPokemonImageByName(evolutionChainDto.evo2Pokemon)

        pokeDexDao.insertEvolutionChain(evolutionChainDto)
    }

    suspend fun getEvolutionChainById(id: Int): EvolutionChainDto = withContext(ioDispatcher) {
        var evolutionChainDto = pokeDexDao.getEvolutionChainById(id)

        if (evolutionChainDto == null) {

            downloadEvolutionChainById(id)
            evolutionChainDto = pokeDexDao.getEvolutionChainById(id)
        }
        return@withContext evolutionChainDto!!
    }

}