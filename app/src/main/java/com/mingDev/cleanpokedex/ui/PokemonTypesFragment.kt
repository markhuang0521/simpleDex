package com.mingDev.cleanpokedex.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mingDev.cleanpokedex.databinding.BottomSheetTypeListBinding
import com.mingDev.cleanpokedex.pokemonDex.PokedexViewModel
import org.koin.android.ext.android.inject

class PokemonTypesFragment(private val clickListener: ClickListener) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetTypeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetTypeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.clickListener = clickListener
    }


    interface ClickListener {
        fun onTypeClick(type: String)
    }




}