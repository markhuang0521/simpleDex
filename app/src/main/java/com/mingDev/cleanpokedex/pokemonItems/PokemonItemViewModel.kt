package com.mingDev.cleanpokedex.pokemonItems


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.database.entity.PokemonItemDto
import com.mingDev.cleanpokedex.repository.PokemonItemRepository
import com.mingDev.cleanpokedex.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class PokemonItemViewModel(private val repository: PokemonItemRepository) : ViewModel() {
    val allAbilities = MutableLiveData<List<PokemonItemDto>>()
    val pokemonsLearnByMove = MutableLiveData<List<PokemonDto>>()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoResult: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        viewModelScope.launch {
            loadFullItems()
        }
    }

    private fun loadFullItems() {
        viewModelScope.launch {
            showLoading.value = true
            allAbilities.value = repository.getAllItems()
            showLoading.postValue(false)

        }
    }
}