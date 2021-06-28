package com.mingDev.cleanpokedex.pokemonCaughtList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.database.entity.PokemonDtoUpdate
import com.mingDev.cleanpokedex.databinding.FragmentCaughtListBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.ui.PokemonAdapter
import com.mingDev.cleanpokedex.ui.PokemonListener
import org.koin.android.ext.android.inject


class CaughtListFragment : Fragment(), PokemonListener {
    private lateinit var binding: FragmentCaughtListBinding

    private val viewModel: PokedexViewModel by inject()
    private lateinit var pokeDexAdapter: PokemonAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCaughtListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)


        setUpRecyclerView()
        setUpObserver()
    }

    private fun setUpRecyclerView() {
        pokeDexAdapter = PokemonAdapter(this)


        binding.recyclerPokemonList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokeDexAdapter

        }

    }

    private fun setUpObserver() {
        viewModel.caughtPokemons.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokeDexAdapter.submitList(it)
            }
        })


    }

    override fun onCardViewClick(pokemon: PokemonDto) {
        viewModel.setSelectedPokemon(pokemon)
        viewModel.setSelectedPokemonDetail(pokemon.speciesName)
        findNavController().navigate(
            CaughtListFragmentDirections.actionCaughtListFragmentToPokemonDetailActivity(pokemon.types[0])
        )

    }

    override fun onCatchBtnClick(pokemon: PokemonDto) {
        viewModel.updatePokemon(
            PokemonDtoUpdate(
                pokemon.name,
                pokemon.isCaught,
                pokemon.isImageShown
            )
        )
        viewModel.loadCaughtPokemons()


    }

    override fun onImageClick(pokemon: PokemonDto) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_caught_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_caught_list -> {
                findNavController().navigate(CaughtListFragmentDirections.actionCaughtListFragmentToPokemonListFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCaughtPokemons()
    }

}