package com.mingDev.cleanpokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mingDev.cleanpokedex.databinding.ItemPokemonTypeBinding
import com.mingDev.cleanpokedex.network.responses.PokemonTypes

class PokemonTypeAdapter : RecyclerView.Adapter<TypeViewHolder>() {
    private var typeList = mutableListOf<String>()


    fun addData(list: List<String>) {
        typeList.clear()
        typeList.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        return TypeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = typeList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return typeList.size

    }


}

class TypeViewHolder(private val binding: ItemPokemonTypeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.pokemonType = item
        binding.executePendingBindings()


    }

    companion object {
        fun from(parent: ViewGroup): TypeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemPokemonTypeBinding.inflate(layoutInflater, parent, false)


            return TypeViewHolder(binding)
        }
    }


}
