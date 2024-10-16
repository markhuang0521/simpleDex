package com.mingDev.cleanpokedex.pokemonMove

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.repository.MoveSetRepository
import com.mingDev.cleanpokedex.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class MoveSetViewModel(private val repository: MoveSetRepository) : ViewModel() {


    val curMoveSets = MutableLiveData<List<MoveSetDto>>()
    val selectedMove = MutableLiveData<MoveSetDto>()
    val pokemonsLearnByMove = MutableLiveData<List<PokemonDto>>()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoResult: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {
        showNoResult.value = false

        viewModelScope.launch {

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

    fun refreshCurList() {

        if (!curMoveSets.value.isNullOrEmpty()) {

            val list = curMoveSets.value!!.map { it.name }

            loadSelectedMoves(list)
        } else {

            loadFullMoveSet()

        }
    }

    private fun loadSelectedMoves(list: List<String>) {
        viewModelScope.launch {
            curMoveSets.value = repository.getSelectedMoves(list)
        }


    }

    fun loadFullMoveSet() {
        viewModelScope.launch {
            showLoading.value = true
            curMoveSets.value = repository.getAllMoveSet()
            showLoading.postValue(false)

        }

    }

    fun searchMovesByName(name: String) {
        viewModelScope.launch {
            val result = repository.searchMovesByName(name)
            curMoveSets.value = result
            showNoResult.value = result.isEmpty()


        }

    }
}