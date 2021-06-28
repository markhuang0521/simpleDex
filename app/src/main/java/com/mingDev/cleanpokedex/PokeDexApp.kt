package com.mingDev.cleanpokedex

import android.app.Application
import dev.marcosfarias.pokedex.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.*

class PokeDexApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())


        startKoin {
            androidContext(this@PokeDexApp)
            modules(appComponent)
        }

    }
}