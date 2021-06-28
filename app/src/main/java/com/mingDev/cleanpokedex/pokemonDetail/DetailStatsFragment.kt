package com.mingDev.cleanpokedex.pokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.databinding.FragmentDetailStatBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.ui.PokemonListener
import org.koin.android.ext.android.inject


class DetailStatsFragment : Fragment(), PokemonListener {
    private lateinit var binding: FragmentDetailStatBinding
    private val viewModel: PokedexViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailStatBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.clickListener = this
    }

    override fun onCardViewClick(pokemon: PokemonDto) {
        // no need for action
    }

    override fun onCatchBtnClick(pokemon: PokemonDto) {
    }

    override fun onImageClick(pokemon: PokemonDto) {
        TODO("Not yet implemented")
    }

}