package dev.marcosfarias.pokedex.di

import com.mingDev.cleanpokedex.database.PokemonDatabase
import com.mingDev.cleanpokedex.repository.AbilityRepository
import com.mingDev.cleanpokedex.repository.MoveSetRepository
import com.mingDev.cleanpokedex.repository.PokemonItemRepository
import com.mingDev.cleanpokedex.repository.PokemonRepository
import org.koin.dsl.module

val databaseModule = module {
    single {
        PokemonDatabase.getInstance(get()).pokeDexDao
    }
    single {
        PokemonDatabase.getInstance(get()).moveSetDao
    }
    single {
        PokemonDatabase.getInstance(get()).abilityDao
    }
    single {
        PokemonDatabase.getInstance(get()).pokemonItemDao
    }


    single {
        PokemonRepository(get(), get())
    }
    single {
        MoveSetRepository(get(), get())
    }
    single {
        AbilityRepository(get(), get())
    }
    single {
        PokemonItemRepository(get(), get())
    }
}
