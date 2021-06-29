package com.mingDev.cleanpokedex.repository

import com.mingDev.cleanpokedex.database.daos.AbilityDao
import com.mingDev.cleanpokedex.database.entity.AbilityDto
import com.mingDev.cleanpokedex.network.PokeApiService
import com.mingDev.cleanpokedex.network.responses.ResultUrl
import com.mingDev.cleanpokedex.utils.toAbilityDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AbilityRepository(
    private val pokeApi: PokeApiService,
    private val abilityDao: AbilityDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    // download data from api
    suspend fun downloadAbility() = withContext(ioDispatcher) {
        val apiResponse = pokeApi.getAbilityList(100, 0)
        val results: List<ResultUrl> = apiResponse.results

        val abilityList = mutableListOf<AbilityDto>()

        results.forEach {

            val ability = pokeApi.getAbilityByName(it.name)
            abilityList.add(ability.toAbilityDto())
        }
        abilityDao.insertAbilityList(abilityList)
    }

    suspend fun getAllAbility(): List<AbilityDto> = withContext(ioDispatcher) {
        return@withContext abilityDao.getAllAbility()
    }

    suspend fun getMoveByName(abilityName: String): AbilityDto =
        withContext(ioDispatcher) {

            return@withContext abilityDao.getAbilityByName(abilityName)
        }

    suspend fun countAbilities(): Int = withContext(ioDispatcher) {
        return@withContext abilityDao.countAbilities()

    }

}