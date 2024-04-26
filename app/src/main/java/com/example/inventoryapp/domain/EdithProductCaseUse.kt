package com.example.inventoryapp.domain

import com.example.inventoryapp.data.Repository
import com.example.inventoryapp.data.model.InventoryEntity

class EdithProductCaseUse(private val repository: Repository) {
    //fun execute(id: Int, name: String, price: Double) = repository.editProduct(id, name, price)
    fun execute(inventoryEntity: InventoryEntity) = repository.editProduct(item = inventoryEntity)
}
