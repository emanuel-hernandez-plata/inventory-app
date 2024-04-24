package com.example.inventoryapp.domain

import com.example.inventoryapp.data.Repository

class LoadDataUseCase(private val repository: Repository) {

    fun execute() = repository.loadInventory()

}