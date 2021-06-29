package com.mingDev.cleanpokedex.network

import com.mingDev.cleanpokedex.network.responses.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*


const val BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, Rfc3339DateJsonAdapter())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(PokeHttpClient.getClient())
    .baseUrl(BASE_URL)
    .build()

interface PokeApiService {

    //    https://pokeapi.co/api/v2/pokemon/
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 0,
        @Query("offset") offset: Int = 0
    ): ApiResponse

    @GET("pokemon/{name}/")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonInfo

    @GET("pokemon-species/{name}/")
    suspend fun getPokemonSpeciesByName(@Path("name") name: String): PokemonSpecies


    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonInfo


    //    https://pokeapi.co/api/v2/move/
    @GET("move")
    suspend fun getMoveSetList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse

    @GET("move/{name}")
    suspend fun getMoveByName(@Path("name") name: String): MoveSetResponse

    //    https://pokeapi.co/api/v2/ability/
    @GET("ability")
    suspend fun getAbilityList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse

    @GET("ability/{name}")
    suspend fun getAbilityByName(@Path("name") name: String): AbilityResponse

    //    https://pokeapi.co/api/v2/item/1
    @GET("item")
    suspend fun getItemList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse

    @GET("item/{name}")
    suspend fun getItemByName(@Path("name") name: String): PokemonItemResponse

}

//object PokeApi {
//    val retrofitService: PokeApiService by lazy {
//        retrofit.create(PokeApiService::class.java)
//    }
//}