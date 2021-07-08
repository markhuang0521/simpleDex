package com.mingDev.cleanpokedex.pokemonAbility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
        setupSearch()
    }

    private fun setUpRecyclerView() {

        abilityAdapter = AbilityAdapter(this)
        binding.recyclerAbilityList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = abilityAdapter
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
                abilityAdapter.submitList(it)
            }
        })
    }

    private fun setupSearch() {
        binding.fabMoveSearch.setOnClickListener {

            binding.searchViewAbilityDex.isIconified = false

            binding.searchViewAbilityDex.visibility = View.VISIBLE

            binding.searchViewAbilityDex.setOnCloseListener {
                binding.searchViewAbilityDex.visibility = View.GONE
                false
            }

            binding.searchViewAbilityDex.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.searchAbilitiesByName(query)
                    }
                    binding.searchViewAbilityDex.visibility = View.GONE

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.searchAbilitiesByName(newText)
                    }
                    return false
                }
            })
        }

    }


    override fun onAbilityClick(ability: AbilityDto) {
        viewModel.setAbility(ability)
        findNavController().navigate(AbilityListFragmentDirections.actionAbilityListFragmentToAbilityDetailFragment())

    }

}