package com.mingDev.cleanpokedex.pokemonMove

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.repository.MoveSetRepository
import com.mingDev.cleanpokedex.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class MoveSetViewModel(private val repository: MoveSetRepository) : ViewModel() {


    val moveSets = MutableLiveData<List<MoveSetDto>>()
    val selectedMove = MutableLiveData<MoveSetDto>()
    val pokemonsLearnByMove = MutableLiveData<List<PokemonDto>>()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoResult: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        viewModelScope.launch {
//           repository.deleteAllMoveSet()
            val num = repository.countMoveSets()

            if (num <= 0) {
                showLoading.value = true

                repository.downloadMoveSets()

            }

            loadFullMoveSet()
        }
    }

    fun setMoveSet(moveSetDto: MoveSetDto) {
        selectedMove.value = moveSetDto
    }

    fun getPokemonsListByMove() {
        showLoading.value = true

        selectedMove.value?.let {
            viewModelScope.launch {

                pokemonsLearnByMove.value = repository.getPokemonListByMove(it.name)

            }
            showLoading.postValue(false)

        }
    }

    fun loadFullMoveSet() {
        viewModelScope.launch {
            showLoading.value = true
            moveSets.value = repository.getAllMoveSet()
            showLoading.postValue(false)

        }

    }

}