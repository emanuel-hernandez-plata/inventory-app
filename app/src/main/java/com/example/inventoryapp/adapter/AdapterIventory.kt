package com.example.inventoryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryapp.R
import com.example.inventoryapp.databinding.InventoryItemBinding
import com.example.inventoryapp.model.InventoryEntity

class AdapterInventory(private val listInventory: List<InventoryEntity>) :
    RecyclerView.Adapter<AdapterInventory.ViewHolderInventory>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderInventory {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InventoryItemBinding.inflate(inflater, parent, false)
        return ViewHolderInventory(binding)
    }

    override fun getItemCount() = listInventory.size

    override fun onBindViewHolder(holder: ViewHolderInventory, position: Int) {
        holder.bind(listInventory[position])
    }

    inner class ViewHolderInventory(private var binding: InventoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(inventory: InventoryEntity) {
            binding.tvNameInventory.text = inventory.name
            binding.tvPriceInventory.text = " $: ${inventory.price}"
        }
    }

}