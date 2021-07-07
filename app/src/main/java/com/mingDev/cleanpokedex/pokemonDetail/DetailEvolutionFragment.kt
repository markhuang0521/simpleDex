package com.mingDev.cleanpokedex.pokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mingDev.cleanpokedex.databinding.FragmentDetailEvolutionBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import org.koin.android.ext.android.inject


class DetailEvolutionFragment : Fragment() {
    private lateinit var binding: FragmentDetailEvolutionBinding
    private val viewModel: PokedexViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.selectedEvolution.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (!it.evo1Trigger.isNullOrEmpty()) {
                    binding.cardEvo1.visibility = View.VISIBLE
                }
                if (!it.evo2Trigger.isNullOrEmpty()) {
                    binding.cardEvo2.visibility = View.VISIBLE
                }
            }
        })
    }


}