package com.mingDev.cleanpokedex.pokemonDex

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.database.entity.PokemonDtoUpdate
import com.mingDev.cleanpokedex.databinding.FragmentPokemonListBinding
import com.mingDev.cleanpokedex.ui.*
import com.mingDev.cleanpokedex.utils.setToolbarColor
import com.mingDev.cleanpokedex.utils.setToolbarTitle
import org.koin.android.ext.android.inject


class PokemonListFragment : Fragment(), PokemonListener,
    PokemonTypesFragment.ClickListener, PokemonGenerationFragment.ClickListener,
    PokemonSortFragment.ClickListener {

    private lateinit var binding: FragmentPokemonListBinding

    private val viewModel: PokedexViewModel by inject()
    private lateinit var pokeDexAdapter: PokemonAdapter
    private lateinit var typeSheetFragment: PokemonTypesFragment
    private lateinit var genSheetFragment: PokemonGenerationFragment
    private lateinit var sortSheetFragment: PokemonSortFragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        setToolbarColor(ContextCompat.getColor(requireContext(), R.color.red))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupBottomSheetFragments()
        setToolbarTitle("Pokedex")
        setHasOptionsMenu(true)

        setUpRecyclerView()
        setUpObserver()
        setupSearch()
        setupFilterByType()
        setupFilterByGen()
        setupSort()

    }


    private fun setupBottomSheetFragments() {
        typeSheetFragment = PokemonTypesFragment(this)
        genSheetFragment = PokemonGenerationFragment(this)
        sortSheetFragment = PokemonSortFragment(this)

    }

    private fun setUpObserver() {
        viewModel.curPokemonList.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokeDexAdapter.submitList(it)
            }
        })
//        viewModel.selectedPokemon.observe(viewLifecycleOwner, Observer {
//            it?.let {
//
//            }
//        })

    }

    private fun setupSearch() {
        binding.fabPokemonSearch.setOnClickListener {

            binding.searchViewPokedex.isIconified = false

            binding.searchViewPokedex.visibility = View.VISIBLE

            binding.searchViewPokedex.setOnCloseListener {
                binding.searchViewPokedex.visibility = View.GONE
                false
            }

            binding.searchViewPokedex.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.searchPokemonsByName(query)
                    }
                    binding.searchViewPokedex.visibility = View.GONE

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.searchPokemonsByName(newText)
                    }
                    return false
                }
            })
        }

    }

    private fun setupSort() {
        binding.fabPokemonSort.setOnClickListener {
            requireActivity().supportFragmentManager.let {
                sortSheetFragment.show(
                    requireActivity().supportFragmentManager,
                    "sortSheetFragment"
                )
            }
        }


    }

    private fun setupFilterByGen() {
        binding.fabPokemonFilterGen.setOnClickListener {
            requireActivity().supportFragmentManager.let {
                genSheetFragment.show(
                    requireActivity().supportFragmentManager,
                    "genSheetFragment"
                )
            }
        }
    }

    private fun setupFilterByType() {

        binding.fabPokemonFilterType.setOnClickListener {
            requireActivity().supportFragmentManager.let {
                typeSheetFragment.show(
                    requireActivity().supportFragmentManager,
                    "typeSheetFragment"
                )
            }
        }

    }


    private fun setUpRecyclerView() {
        pokeDexAdapter = PokemonAdapter(this)
        viewModel.refreshCurList()
        binding.recyclerPokemonList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokeDexAdapter
        }

    }


    override fun onCardViewClick(pokemon: PokemonDto) {
        viewModel.setSelectedPokemon(pokemon)
        viewModel.setSelectedPokemonDetail(pokemon.speciesName)
        findNavController().navigate(
            PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailActivity(pokemon.types[0])
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
    }

    override fun onImageClick(pokemon: PokemonDto) {
        viewModel.updatePokemon(
            PokemonDtoUpdate(
                pokemon.name,
                pokemon.isCaught,
                !pokemon.isImageShown
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_pokedex, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.caughtListFragment -> {
                findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToCaughtListFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTypeClick(type: String) {
        viewModel.setPokemonFilterByType(type)
        typeSheetFragment.dismiss()

    }

    override fun onGenClick(generation: String) {

        viewModel.setPokemonFilterByGeneration(generation)
        genSheetFragment.dismiss()
    }

    override fun onSortClick(sort: String, order: String) {
        viewModel.pokemonSort(sort, order)
        sortSheetFragment.dismiss()
    }


}