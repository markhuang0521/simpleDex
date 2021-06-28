package dev.marcosfarias.pokedex.di

import com.mingDev.cleanpokedex.network.BASE_URL
import com.mingDev.cleanpokedex.network.PokeApiService
import com.mingDev.cleanpokedex.network.PokeHttpClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.*

val networkModule = module {
    single {
        PokeHttpClient.getClient()
    }

    single<Converter.Factory>(named("moshi")) {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
        MoshiConverterFactory.create(moshi)
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get(named("moshi")))
            .build()
    }

    single {
        get<Retrofit>().create<PokeApiService>()
    }
}
