package com.mingDev.cleanpokedex.pokemonMove

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
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.databinding.FragmentMovesListBinding
import com.mingDev.cleanpokedex.ui.MoveSetAdapter
import com.mingDev.cleanpokedex.ui.MoveSetListener
import com.mingDev.cleanpokedex.utils.setToolbarColor
import com.mingDev.cleanpokedex.utils.setToolbarTitle
import org.koin.android.ext.android.inject


class MoveListFragment : Fragment(), MoveSetListener {
    private lateinit var binding: FragmentMovesListBinding
    private val viewModel: MoveSetViewModel by inject()
    private lateinit var moveSetAdapter: MoveSetAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovesListBinding.inflate(inflater, container, false)
        setToolbarColor(ContextCompat.getColor(requireContext(), R.color.electric))
        setToolbarTitle("Move Dex")
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

    private fun setUpObserver() {
        viewModel.moveSets.observe(viewLifecycleOwner, Observer {
            it?.let {
                moveSetAdapter.submitList(it)
            }
        })

    }

    private fun setUpRecyclerView() {
        moveSetAdapter = MoveSetAdapter(this)
        binding.recyclerMoveSetList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moveSetAdapter
        }

    }

    private fun setupSearch() {
        binding.fabMoveSearch.setOnClickListener {

            binding.searchViewMoveDex.isIconified = false

            binding.searchViewMoveDex.visibility = View.VISIBLE

            binding.searchViewMoveDex.setOnCloseListener {
                binding.searchViewMoveDex.visibility = View.GONE
                false
            }

            binding.searchViewMoveDex.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.searchMovesByName(query)
                    }
                    binding.searchViewMoveDex.visibility = View.GONE

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.searchMovesByName(newText)
                    }
                    return false
                }
            })
        }

    }


//    private fun setupBottomSheetFragments() {
//        moveDetailFragment = MoveListDetailFragment()
//    }

    override fun onMoveSetClick(move: MoveSetDto) {
        viewModel.setMoveSet(move)
//        requireActivity().supportFragmentManager.let {
//
//            moveDetailFragment.show(
//                requireActivity().supportFragmentManager,
//                "moveDetailFragment"
//            )
//        }
        findNavController().navigate(MoveListFragmentDirections.actionMoveListFragmentToMoveListDetailFragment())
    }


}