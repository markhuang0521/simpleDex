package dev.marcosfarias.pokedex.di

import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.pokemonMove.MoveSetViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { PokedexViewModel(get()) }
    single { MoveSetViewModel(get()) }

}
