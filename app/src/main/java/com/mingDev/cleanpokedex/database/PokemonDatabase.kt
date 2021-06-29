package com.mingDev.cleanpokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mingDev.cleanpokedex.database.daos.AbilityDao
import com.mingDev.cleanpokedex.database.daos.MoveSetDao
import com.mingDev.cleanpokedex.database.daos.PokeDexDao
import com.mingDev.cleanpokedex.database.daos.PokemonItemDao
import com.mingDev.cleanpokedex.database.entity.*

@TypeConverters(PokemonConverter::class)
@Database(
    entities = [
        PokemonDto::class,
        PokemonDetailDto::class,
        MoveSetDto::class,
        AbilityDto::class,
        PokemonItemDto::class
    ],

    version = 26,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {


    abstract val pokeDexDao: PokeDexDao
    abstract val moveSetDao: MoveSetDao
    abstract val abilityDao: AbilityDao
    abstract val pokemonItemDao: PokemonItemDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDatabase::class.java,
                        "pokemon_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}