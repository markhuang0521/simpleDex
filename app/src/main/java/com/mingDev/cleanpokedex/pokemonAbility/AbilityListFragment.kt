package com.mingDev.cleanpokedex.pokemonAbility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.database.entity.AbilityDto
import com.mingDev.cleanpokedex.databinding.FragmentAbilityListBinding
import com.mingDev.cleanpokedex.ui.AbilityAdapter
import com.mingDev.cleanpokedex.ui.AbilityListener
import com.mingDev.cleanpokedex.utils.setToolbarColor
import com.mingDev.cleanpokedex.utils.setToolbarTitle
import org.koin.android.ext.android.inject


class AbilityListFragment : Fragment(), AbilityListener {
    private lateinit var binding: FragmentAbilityListBinding
    private val viewModel: AbilityViewModel by inject()
    private lateinit var abilityAdapter: AbilityAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAbilityListBinding.inflate(inflater, container, false)
        setToolbarColor(ContextCompat.getColor(requireContext(), R.color.water))
        setToolbarTitle("Ability Dex")
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

        abilityAdapter = AbilityAdapter(this)
        binding.recyclerAbilityList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = abilityAdapter
        }
    }

    private fun setUpObserver() {
        viewModel.allAbilities.observe(viewLifecycleOwner, Observer {
            it?.let {
                abilityAdapter.submitList(it)
            }
        })
    }

    override fun onAbilityClick(ability: AbilityDto) {
        viewModel.setAbility(ability)
        findNavController().navigate(AbilityListFragmentDirections.actionAbilityListFragmentToAbilityDetailFragment())

    }

}