package com.mingDev.cleanpokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mingDev.cleanpokedex.database.entity.AbilityDto
import com.mingDev.cleanpokedex.databinding.ItemAbilityBinding

class AbilityAdapter(private val clickListener: AbilityListener) :
    ListAdapter<AbilityDto, AbilityViewHolder>((AbilityDiffCallback())) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val ability = getItem(position)
        holder.bind(ability, clickListener)
    }

}

class AbilityViewHolder(private val binding: ItemAbilityBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AbilityDto, clickListener: AbilityListener) {
        binding.ability = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): AbilityViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemAbilityBinding.inflate(inflater, parent, false)

            return AbilityViewHolder(binding)
        }
    }

}

class AbilityDiffCallback : DiffUtil.ItemCallback<AbilityDto>() {
    override fun areItemsTheSame(oldItem: AbilityDto, newItem: AbilityDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AbilityDto, newItem: AbilityDto): Boolean {
        return oldItem == newItem
    }
}

interface AbilityListener {
    fun onAbilityClick(ability: AbilityDto)

}
