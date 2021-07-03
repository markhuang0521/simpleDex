package com.mingDev.cleanpokedex.pokemonDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.databinding.FragmentDetailMovesBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import com.mingDev.cleanpokedex.ui.MoveSetAdapter
import org.koin.android.ext.android.inject


class DetailEvolutionFragment : Fragment() {
    private lateinit var binding: FragmentDetailMovesBinding
    private val viewModel: PokedexViewModel by inject()
    private lateinit var moveSetAdapter: MoveSetAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_evolution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}