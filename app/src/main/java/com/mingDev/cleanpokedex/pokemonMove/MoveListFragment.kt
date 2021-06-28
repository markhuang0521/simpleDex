package com.mingDev.cleanpokedex.pokemonMove

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.databinding.FragmentMovesListBinding
import com.mingDev.cleanpokedex.ui.MoveListDetailFragment
import com.mingDev.cleanpokedex.ui.MoveSetAdapter
import com.mingDev.cleanpokedex.ui.MoveSetListener
import com.mingDev.cleanpokedex.utils.setToolbarColor
import com.mingDev.cleanpokedex.utils.setToolbarTitle
import org.koin.android.ext.android.inject


class MoveListFragment : Fragment(), MoveSetListener {
    private lateinit var binding: FragmentMovesListBinding
    private val viewModel: MoveSetViewModel by inject()
    private lateinit var moveSetAdapter: MoveSetAdapter

    private lateinit var moveDetailFragment: MoveListDetailFragment


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
        setupBottomSheetFragments()
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

    private fun setupBottomSheetFragments() {
        moveDetailFragment = MoveListDetailFragment()
    }

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