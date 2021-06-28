package com.mingDev.cleanpokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mingDev.cleanpokedex.databinding.FragmentPokemonSortBinding

class PokemonSortFragment(private val clickListener: ClickListener) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPokemonSortBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonSortBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.clickListener = clickListener

        binding.btnSort.setOnClickListener {
            // using invisiable hint as the string for the sorting search
            val selectedSort =
                binding.rgSort.findViewById<RadioButton>(binding.rgSort.checkedRadioButtonId)
            val selectedOrder =
                binding.rgOrder.findViewById<RadioButton>(binding.rgOrder.checkedRadioButtonId)
            clickListener.onSortClick(selectedSort.hint.toString(), selectedOrder.hint.toString())
        }
    }


    interface ClickListener {
        fun onSortClick(sort: String, order: String)
    }
}