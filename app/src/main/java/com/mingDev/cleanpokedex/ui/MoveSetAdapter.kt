package com.mingDev.cleanpokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.databinding.ItemMoveSetBinding

class MoveSetAdapter(private val clickListener: MoveSetListener) :
    ListAdapter<MoveSetDto, MoveSetViewHolder>((MoveDiffCallback())) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveSetViewHolder {
        return MoveSetViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MoveSetViewHolder, position: Int) {
        val move = getItem(position)
        holder.bind(move, clickListener)
    }

}

class MoveSetViewHolder(private val binding: ItemMoveSetBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MoveSetDto, clickListener: MoveSetListener) {
        binding.move = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): MoveSetViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMoveSetBinding.inflate(inflater, parent, false)

            return MoveSetViewHolder(binding)
        }
    }

}

class MoveDiffCallback : DiffUtil.ItemCallback<MoveSetDto>() {
    override fun areItemsTheSame(oldItem: MoveSetDto, newItem: MoveSetDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoveSetDto, newItem: MoveSetDto): Boolean {
        return oldItem == newItem
    }
}

interface MoveSetListener {
    fun onMoveSetClick(move: MoveSetDto)

}
