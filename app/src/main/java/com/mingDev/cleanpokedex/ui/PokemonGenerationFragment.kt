package com.mingDev.cleanpokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mingDev.cleanpokedex.databinding.FragmentPokemonGensBinding

class PokemonGenerationFragment(private val clickListener: ClickListener) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPokemonGensBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonGensBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.clickListener = clickListener
    }


    interface ClickListener {
        fun onGenClick(generation: String)
    }
}