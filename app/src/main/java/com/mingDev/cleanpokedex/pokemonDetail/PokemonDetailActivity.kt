package com.mingDev.cleanpokedex.pokemonDetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityPokemonDetailBinding
    private val args by navArgs<PokemonDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        navController = findNavController(R.id.nav_host_fragment_activity_pokemon_detail)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.detailStatsFragment, R.id.detailMovesFragment, R.id.detailMoreFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavPokemonDetails.setupWithNavController(navController)

        // args from list fragment to selected bg color for bottom nav
        val color = ContextCompat.getColor(
            this, resources.getIdentifier(
                args.typeColor, "color", this.packageName
            )
        )

        binding.bottomNavPokemonDetails.setBackgroundColor(color)
    }

    override fun onNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        )
    }
}