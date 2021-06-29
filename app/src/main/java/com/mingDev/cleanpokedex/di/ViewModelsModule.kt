package dev.marcosfarias.pokedex.di

import com.mingDev.cleanpokedex.pokemonAbility.AbilityViewModel
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.pokemonItems.PokemonItemViewModel
import com.mingDev.cleanpokedex.pokemonMove.MoveSetViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { PokedexViewModel(get()) }
    single { MoveSetViewModel(get()) }
    single { AbilityViewModel(get()) }
    single { PokemonItemViewModel(get()) }

}
