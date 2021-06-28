package com.mingDev.cleanpokedex.pokemonAbility

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingDev.cleanpokedex.database.entity.AbilityDto
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.repository.AbilityRepository
import com.mingDev.cleanpokedex.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class AbilityViewModel(private val repository: AbilityRepository) : ViewModel() {
    val AllAbilities = MutableLiveData<List<AbilityDto>>()
    val selectedMove = MutableLiveData<MoveSetDto>()
    val pokemonsLearnByMove = MutableLiveData<List<PokemonDto>>()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoResult: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        viewModelScope.launch {
//           repository.deleteAllMoveSet()
            val num = repository.countAbilities()
            if (num <= 0) {
                showLoading.value = true

                repository.downloadAbility()

            }

            loadFullAbilities()
        }
    }

    private fun loadFullAbilities() {
        viewModelScope.launch {
            showLoading.value = true
            AllAbilities.value = repository.getAllAbility()
            showLoading.postValue(false)

        }
    }
}