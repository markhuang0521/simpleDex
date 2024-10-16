package com.mingDev.cleanpokedex.pokemonDex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingDev.cleanpokedex.database.entity.*
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
    val selectedEvolution = MutableLiveData<EvolutionChainDto>()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoResult: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val selectedSort = MutableLiveData<String>()
    val selectedOrder = MutableLiveData<String>()

    init {
        showNoResult.value = false
        viewModelScope.launch {

//            repository.deleteAllPokemon()


            refreshCurList()
        }
    }

    fun refreshCurList() {

        if (!curPokemonList.value.isNullOrEmpty()) {
            showLoading.value = true

            val list = curPokemonList.value!!.map { it.name }

            loadSelectedPokemons(list)
            showLoading.postValue(false)

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

    fun getPokemonEvolution() {
        selectedPokemonDetail.value?.let {
            viewModelScope.launch {
                Timber.d("evo id" + it.evoIndex.toString())
                selectedEvolution.value = repository.getEvolutionChainById(it.evoIndex!!)

            }
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
            showNoResult.value = result.isEmpty()

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
            getPokemonEvolution()
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