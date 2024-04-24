package com.example.inventoryapp.data

import androidx.lifecycle.MutableLiveData
import com.example.inventoryapp.data.model.InventoryEntity

class Repository {
    val listInventory = MutableLiveData<List<InventoryEntity>>()
    private fun getListInventory() = listOf(
        InventoryEntity(id = 1, image = 3, name = "awa", price = 6000.0),
        InventoryEntity(id = 2, image = 3, name = "lentes de contacto", price = 6500.0),
        InventoryEntity(id = 3, image = 3, name = "arepa mixta", price = 8500.0),
        InventoryEntity(id = 4, image = 3, name = "caguama", price = 10000.0),
    )

    fun loadInventory() {
        listInventory.postValue(getListInventory())
    }

    fun deleteData(id: Int) {
        listInventory.postValue(listInventory.value?.filter { it.id != id })
    }

    fun createProduct(name: String, price: Double) {
        val id = listInventory.value?.maxByOrNull { it.id }?.id?.plus(1) ?: 1

        listInventory.postValue(listInventory.value?.plus(InventoryEntity(id = id, image = 3 , name = name, price = price)))

    }

}