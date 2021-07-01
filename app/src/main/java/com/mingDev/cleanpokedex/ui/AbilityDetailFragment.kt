package com.mingDev.cleanpokedex.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.database.entity.PokemonDtoUpdate
import com.mingDev.cleanpokedex.databinding.FragmentAbilityDetailBinding
import com.mingDev.cleanpokedex.pokemonAbility.AbilityViewModel
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.utils.setToolbarTitle
import org.koin.android.ext.android.inject


class AbilityDetailFragment : Fragment(), PokemonListener {
    private lateinit var binding: FragmentAbilityDetailBinding
    private val abilityViewModel: AbilityViewModel by inject()
    private val pokedexViewModel: PokedexViewModel by inject()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAbilityDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = abilityViewModel
        setToolbarTitle(abilityViewModel.selectedAbility.value?.name ?: "")

        // scrolling textview in fragment
        binding.tvDetailDesc.movementMethod = ScrollingMovementMethod()
        setUpObserver()
        setUpRecyclerView()
    }

    private fun setUpObserver() {
        abilityViewModel.pokemonsWithAbility.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokemonAdapter.submitList(it)
            }
        })

    }

    private fun setUpRecyclerView() {
        pokemonAdapter = PokemonAdapter(this)
        abilityViewModel.getPokemonsListByMove()
        binding.recyclerPokemonLearnedList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonAdapter
        }

    }

    override fun onCardViewClick(pokemon: PokemonDto) {

        pokedexViewModel.setSelectedPokemon(pokemon)
        pokedexViewModel.setSelectedPokemonDetail(pokemon.speciesName)
        findNavController().navigate(
            MoveListDetailFragmentDirections.actionMoveListDetailFragmentToPokemonDetailActivity(
                pokemon.types[0]
            )
        )
    }

    override fun onCatchBtnClick(pokemon: PokemonDto) {
        pokedexViewModel.updatePokemon(
            PokemonDtoUpdate(
                pokemon.name,
                pokemon.isCaught,
                pokemon.isImageShown
            )
        )
    }

    override fun onImageClick(pokemon: PokemonDto) {
        //
    }

}