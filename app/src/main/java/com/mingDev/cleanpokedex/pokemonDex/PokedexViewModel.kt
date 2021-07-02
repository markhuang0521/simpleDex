package com.mingDev.cleanpokedex.pokemonDex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDetailDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.database.entity.PokemonDtoUpdate
import com.mingDev.cleanpokedex.repository.PokemonRepository
import com.mingDev.cleanpokedex.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class PokedexViewModel(private val repository: PokemonRepository) : ViewModel() {

    val curPokemonList = MutableLiveData<List<PokemonDto>>()
    val caughtPokemons = MutableLiveData<List<PokemonDto>>()

    val selectedPokemon = MutableLiveData<PokemonDto>()
    val selectedPokemonDetail = MutableLiveData<PokemonDetailDto>()
    val selectedMoveSet = MutableLiveData<List<MoveSetDto>>()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoResult: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val selectedSort = MutableLiveData<String>()
    val selectedOrder = MutableLiveData<String>()

    init {
        viewModelScope.launch {

//            repository.deleteAllPokemon()

            val num = repository.countPokemons()
            Timber.d("total pokemon in db" + num.toString())

            if (num <= 0) {
                Timber.d(num.toString())
                showLoading.value = true

                repository.downloadPokedex()
            }
            refreshCurList()
        }
    }

    fun refreshCurList() {
        Timber.d("current sorted pokemon list" + curPokemonList.value.toString())

        if (!curPokemonList.value.isNullOrEmpty()) {

            val list = curPokemonList.value!!.map { it.name }
            Timber.d("current sorted pokemon list2" + list.toString())

            loadSelectedPokemons(list)
        } else {

            loadFullPokeDex()

        }
    }

    fun loadFullPokeDex() {

        viewModelScope.launch {
            showLoading.value = true
            curPokemonList.value = repository.getAllPokemons()
            showLoading.postValue(false)

        }
    }


    fun getPokemonMoveSet() {
        selectedPokemonDetail.value?.let {
            viewModelScope.launch {
                selectedMoveSet.value = repository.getMovesByList(it.moveSet)
            }
        }
    }


    fun setPokemonFilterByType(type: String?) {
        viewModelScope.launch {
            if (type.isNullOrEmpty()) {
                loadFullPokeDex()
            } else {
                curPokemonList.value = repository.filterPokemonsByType(type)

            }
        }
    }

    fun setPokemonFilterByGeneration(generation: String?) {
        viewModelScope.launch {
            if (generation.isNullOrEmpty()) {
                loadFullPokeDex()
            } else {
                curPokemonList.value = repository.filterPokemonsByGen(generation)

            }
        }
    }

    fun pokemonSort(sort: String, order: String) {

        viewModelScope.launch {
            curPokemonList.value = repository.getPokemonSortBy(sort, order)


        }
    }

    fun searchPokemonsByName(name: String) {
        viewModelScope.launch {

            val result = repository.searchPokemonsByName(name)
            curPokemonList.value = result
        }
    }


    fun loadSelectedPokemons(pokemonList: List<String>) {
        viewModelScope.launch {

            curPokemonList.value = repository.getSelectedPokemons(pokemonList)
        }
    }

    fun loadCaughtPokemons() {
        showLoading.value = true
        viewModelScope.launch {
            caughtPokemons.value = repository.getCaughtPokemons()
            showLoading.postValue(false)
        }
    }

    fun setSelectedPokemon(pokemon: PokemonDto) {
        selectedPokemon.value = pokemon

    }

    fun setSelectedPokemonDetail(speciesName: String) {
        viewModelScope.launch {

            selectedPokemonDetail.value = repository.getPokemonDetailByName(speciesName)
        }
    }

    fun updatePokemon(pokemon: PokemonDtoUpdate) {
        viewModelScope.launch {
            showLoading.value = true

            repository.updatePokemon(pokemon)
            showLoading.postValue(false)

        }
        refreshCurList()
    }


}