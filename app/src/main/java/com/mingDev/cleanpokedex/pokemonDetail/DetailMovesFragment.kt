package com.mingDev.cleanpokedex.pokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.databinding.FragmentDetailMovesBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.ui.MoveSetAdapter
import com.mingDev.cleanpokedex.ui.MoveSetListener
import com.mingDev.cleanpokedex.ui.PokemonListener
import org.koin.android.ext.android.inject


class DetailMovesFragment : Fragment(), PokemonListener, MoveSetListener {
    private lateinit var binding: FragmentDetailMovesBinding
    private val viewModel: PokedexViewModel by inject()
    private lateinit var moveSetAdapter: MoveSetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.clickListener = this
        setUpRecyclerView()
        setUpObserver()
    }


    private fun setUpRecyclerView() {
        moveSetAdapter = MoveSetAdapter(this)
        viewModel.getPokemonMoveSet()
        binding.recyclerMoveSetList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moveSetAdapter
        }

    }

    private fun setUpObserver() {
        viewModel.selectedMoveSet.observe(viewLifecycleOwner, Observer {
            it?.let {
                moveSetAdapter.submitList(it)
            }
        })

    }

    override fun onCardViewClick(pokemon: PokemonDto) {
        // no need for action

    }

    override fun onCatchBtnClick(pokemon: PokemonDto) {
    }

    override fun onImageClick(pokemon: PokemonDto) {
    }

    override fun onMoveSetClick(pokemon: MoveSetDto) {
//    "open move detail fragment"
    }

}