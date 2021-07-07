package com.mingDev.cleanpokedex.pokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mingDev.cleanpokedex.databinding.FragmentDetailMoreBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import org.koin.android.ext.android.inject


class DetailMoreFragment : Fragment() {
    private lateinit var binding: FragmentDetailMoreBinding
    private val viewModel: PokedexViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}