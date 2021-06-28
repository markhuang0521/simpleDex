package com.mingDev.cleanpokedex.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.databinding.ItemPokemonBinding

class PokemonAdapter(private val clickListener: PokemonListener) :
    ListAdapter<PokemonDto, PokemonViewHolder>(PokemonDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon, clickListener)
    }


//    fun addData(list: List<PokemonDto>) {
//        pokemonList.clear()
//        pokemonList.addAll(list)
//        notifyDataSetChanged()
//
//    }
}

class PokemonViewHolder(private val binding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PokemonDto, clickListener: PokemonListener) {
        binding.pokemon = item
        binding.clickListener = clickListener

        if (!item.isImageShown) {
            binding.ivPokemon.alpha = 0.4F
            ImageViewCompat.setImageTintList(
                binding.ivPokemon,
                ColorStateList.valueOf(Color.WHITE)
            );

        } else {
            binding.ivPokemon.alpha = 1F
            binding.ivPokemon.imageTintList = null
        }

        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): PokemonViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPokemonBinding.inflate(inflater, parent, false)

            return PokemonViewHolder(binding)
        }
    }
}

class PokemonDiffCallback : DiffUtil.ItemCallback<PokemonDto>() {
    override fun areItemsTheSame(oldItem: PokemonDto, newItem: PokemonDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonDto, newItem: PokemonDto): Boolean {
        return oldItem == newItem
    }


}

interface PokemonListener {
    fun onCardViewClick(pokemon: PokemonDto)
    fun onCatchBtnClick(pokemon: PokemonDto)
    fun onImageClick(pokemon: PokemonDto)


}

