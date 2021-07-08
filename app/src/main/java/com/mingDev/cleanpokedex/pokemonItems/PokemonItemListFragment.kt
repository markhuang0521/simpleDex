package com.mingDev.cleanpokedex.pokemonItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.databinding.FragmentPokemonItemListBinding
import com.mingDev.cleanpokedex.ui.PokemonItemAdapter
import com.mingDev.cleanpokedex.utils.setToolbarColor
import com.mingDev.cleanpokedex.utils.setToolbarTitle
import org.koin.android.ext.android.inject


class PokemonItemListFragment : Fragment() {
    private lateinit var binding: FragmentPokemonItemListBinding
    private val viewModel: PokemonItemViewModel by inject()
    private lateinit var pokemonItemAdapter: PokemonItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonItemListBinding.inflate(inflater, container, false)
        setToolbarColor(ContextCompat.getColor(requireContext(), R.color.grass))
        setToolbarTitle("Item Dex")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setUpObserver()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        pokemonItemAdapter = PokemonItemAdapter()
        binding.recyclerItemList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonItemAdapter
        }
    }

    private fun setUpObserver() {
        viewModel.showLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.progressBar.visibility=View.VISIBLE
                binding.textLoading.visibility=View.VISIBLE

            }else{
                binding.progressBar.visibility=View.GONE
                binding.textLoading.visibility=View.GONE
            }
        })
        viewModel.allAbilities.observe(viewLifecycleOwner, Observer {
            it?.let {
                pokemonItemAdapter.submitList(it)
            }
        })
    }


}