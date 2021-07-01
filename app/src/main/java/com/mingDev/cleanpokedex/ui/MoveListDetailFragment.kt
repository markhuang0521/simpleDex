package com.mingDev.cleanpokedex.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.database.entity.PokemonDtoUpdate
import com.mingDev.cleanpokedex.databinding.FragmentMoveListDetailBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.pokemonMove.MoveSetViewModel
import com.mingDev.cleanpokedex.utils.setToolbarTitle
import org.koin.android.ext.android.inject
import timber.log.Timber


class MoveListDetailFragment : BottomSheetDialogFragment(), PokemonListener {
    private lateinit var binding: FragmentMoveListDetailBinding
    private val moveSetViewModel: MoveSetViewModel by inject()
    private val pokedexViewModel: PokedexViewModel by inject()
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoveListDetailBinding.inflate(inflater, container, false)
//        showTitleBar(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = moveSetViewModel
        setToolbarTitle(moveSetViewModel.selectedMove.value?.name ?: "")

        // scrolling textview in fragment
        binding.tvDetailDesc.movementMethod = ScrollingMovementMethod()
        setUpObserver()
        setUpRecyclerView()
    }

    private fun setUpObserver() {
        moveSetViewModel.pokemonsLearnByMove.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokemonAdapter.submitList(it)
            }
        })

    }

    private fun setUpRecyclerView() {
        pokemonAdapter = PokemonAdapter(this)
        moveSetViewModel.getPokemonsListByMove()
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
        // no implementation
    }

}