package com.example.inventoryapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.inventoryapp.databinding.InventoryItemBinding
import com.example.inventoryapp.data.model.InventoryEntity

class AdapterInventory(
    private val listInventoryLiveData: MutableLiveData<List<InventoryEntity>>,
    private val listener: (InventoryEntity, View) -> Unit
) : RecyclerView.Adapter<AdapterInventory.ViewHolderInventory>() {

    var listInventory = listOf<InventoryEntity>()

    init {
        listInventoryLiveData.observeForever {
            listInventory = it
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderInventory {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InventoryItemBinding.inflate(inflater, parent, false)
        return ViewHolderInventory(binding)
    }

    override fun getItemCount() = listInventory.size

    override fun onBindViewHolder(holder: ViewHolderInventory, position: Int) {
        holder.bind(listInventory[position])
    }

    inner class ViewHolderInventory(private var binding: InventoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(inventory: InventoryEntity) {
            binding.btnThreeDotMenu.setOnClickListener {
                listener(inventory, it)
            }
            binding.tvNameInventory.text = inventory.name
            binding.tvPriceInventory.text = " $: ${inventory.price}"
        }
    }
}