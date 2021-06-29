package com.mingDev.cleanpokedex.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mingDev.cleanpokedex.database.entity.AbilityDto
import com.mingDev.cleanpokedex.database.entity.PokemonItemDto
import com.mingDev.cleanpokedex.databinding.ItemAbilityBinding
import com.mingDev.cleanpokedex.databinding.ItemPokemonItemBinding

class PokemonItemAdapter() : ListAdapter<PokemonItemDto, ItemViewHolder>((ItemDiffCallback())) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val ability = getItem(position)
        holder.bind(ability)
    }

}

class ItemViewHolder(private val binding: ItemPokemonItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PokemonItemDto) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPokemonItemBinding.inflate(inflater, parent, false)

            return ItemViewHolder(binding)
        }
    }

}

class ItemDiffCallback : DiffUtil.ItemCallback<PokemonItemDto>() {
    override fun areItemsTheSame(oldItem: PokemonItemDto, newItem: PokemonItemDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonItemDto, newItem: PokemonItemDto): Boolean {
        return oldItem == newItem
    }
}


