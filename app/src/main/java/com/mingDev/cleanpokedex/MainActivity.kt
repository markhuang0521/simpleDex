package com.mingDev.cleanpokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mingDev.cleanpokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = this.findNavController(R.id.nav_host_fragment)

        val topLevelDestinations = mutableSetOf<Int>()
        topLevelDestinations.add(R.id.abilityListFragment)
        topLevelDestinations.add(R.id.moveListFragment)
        topLevelDestinations.add(R.id.pokemonItemsFragment)
        topLevelDestinations.add(R.id.pokemonListFragment)
        topLevelDestinations.add(R.id.caughtListFragment)
        appBarConfiguration = AppBarConfiguration(topLevelDestinations, binding.drawerLayout)
        // setup drawer layout btn in appbar
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        // setup drawer menu item listner
        binding.navViewPokedex.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        )
    }


}