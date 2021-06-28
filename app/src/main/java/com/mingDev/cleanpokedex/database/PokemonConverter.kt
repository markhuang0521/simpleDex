package com.mingDev.cleanpokedex.database

import androidx.room.TypeConverter
import com.mingDev.cleanpokedex.network.responses.PokemonInfo
import com.mingDev.cleanpokedex.network.responses.PokemonSpecies
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class PokemonConverter {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val infoAdapter = moshi.adapter(PokemonInfo::class.java)
    private val speciesAdapter = moshi.adapter(PokemonSpecies::class.java)

    private val stringsType = Types.newParameterizedType(List::class.java, String::class.java)
    private val stringAdapter = moshi.adapter<List<String>>(stringsType)


    @TypeConverter
    @FromJson
    fun fromJsonToTypes(json: String?): List<String>? {
        json?.let {
            return stringAdapter.fromJson(json)
        }
        return null

    }

    @TypeConverter
    @ToJson
    fun typeListToJson(types: List<String>): String {
        return stringAdapter.toJson(types)
    }


    @TypeConverter
    @FromJson
    fun fromJsonToPokemon(json: String?): PokemonInfo? {
        json?.let {
            return infoAdapter.fromJson(json)
        }
        return null

    }

    @TypeConverter
    @ToJson
    fun pokemonToJson(pokemonInfoResponse: PokemonInfo): String {
        return infoAdapter.toJson(pokemonInfoResponse)
    }


    @TypeConverter
    @FromJson
    fun fromJsonToPokemonSpecies(json: String?): PokemonSpecies? {
        json?.let {
            return speciesAdapter.fromJson(json)
        }
        return null

    }

    @TypeConverter
    @ToJson
    fun pokemonSpeciesToJson(pokemonSpecies: PokemonSpecies): String {
        return speciesAdapter.toJson(pokemonSpecies)
    }


}