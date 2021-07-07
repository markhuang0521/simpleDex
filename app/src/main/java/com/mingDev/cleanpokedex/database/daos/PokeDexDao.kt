package com.mingDev.cleanpokedex.database.daos

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.mingDev.cleanpokedex.database.entity.*
import com.mingDev.cleanpokedex.utils.STRING_ORDER_ASCENDING


@Dao
interface PokeDexDao {

    // get evolution chain from tb_evolution
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvolutionChain(evolutionChainDto: EvolutionChainDto)

    @Query("SELECT * FROM tb_evolution where id=:id limit 1")
    suspend fun getEvolutionChainById(id: Int): EvolutionChainDto?

    // get species from tb_species
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemonSpecies(pokemons: List<PokemonDetailDto>)

    @Query("SELECT * FROM tb_species where name=:name limit 1")
    suspend fun getPokemonDetailByName(name: String): PokemonDetailDto

    // get moveset from tb_moveSets
    @Query("SELECT * FROM tb_moveSets where name in (:moveList) ORDER BY id")
    fun getMovesByList(moveList: List<String>): List<MoveSetDto>

    // get pokemon from tb_pokemons
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemons(pokemons: List<PokemonDto>)

    @Query("SELECT * FROM tb_pokemons")
    fun getAllPokemons(): List<PokemonDto>

    @Query("SELECT * FROM tb_pokemons where name in(:pokemonList) ORDER BY id")
    fun getPokemonsByNameListOrderById(pokemonList: List<String>): List<PokemonDto>

    @Query("SELECT * FROM tb_pokemons where id=:id limit 1")
    suspend fun getPokemonById(id: Int): PokemonDto

    @Query("SELECT * FROM tb_pokemons where name=:name limit 1")
    suspend fun getPokemonByName(name: String): PokemonDto

    @Query("SELECT imageUrl FROM tb_pokemons where speciesName=:name limit 1")
    suspend fun getPokemonImageByName(name: String?): String?

    @Query("Delete  FROM tb_pokemons")
    suspend fun deleteAllPokemons()

    @Query("SELECT COUNT(*)  FROM tb_pokemons")
    fun countPokemons(): Int

    @Update(entity = PokemonDto::class)
    suspend fun updatePokemon(pokemonDtoIsCaught: PokemonDtoUpdate)

    @Query("SELECT * FROM tb_pokemons where isCaught=1")
    fun getCaughtPokemons(): List<PokemonDto>

    @Query("SELECT * FROM tb_pokemons where name LIKE '%' || :name || '%' or idString LIKE '%' || :name || '%'  ")
    fun searchPokemonsByName(name: String): List<PokemonDto>

    @Query("SELECT * FROM tb_pokemons where types like '%' || :type || '%'  ")
    fun filterPokemonsByType(type: String): List<PokemonDto>

    @Query("SELECT * FROM tb_pokemons where generation=:generation")
    fun filterPokemonsByGen(generation: String): List<PokemonDto>

    @RawQuery
    fun getSortedPokemonByRawQuery(query: SupportSQLiteQuery): List<PokemonDto>

    fun getPokemonSortBy(sort: String, order: String): List<PokemonDto> {

        val statement = if (order == STRING_ORDER_ASCENDING) {
            "SELECT * FROM tb_pokemons ORDER BY $sort ASC"
        } else {
            "SELECT * FROM tb_pokemons ORDER BY $sort DESC"
        }
        val query: SupportSQLiteQuery = SimpleSQLiteQuery(statement, arrayOf<PokemonDto>())
        return getSortedPokemonByRawQuery(query)

    }


}
