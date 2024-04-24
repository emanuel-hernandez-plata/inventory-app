package com.example.inventoryapp.domain

import com.example.inventoryapp.data.Repository

class GetDataUseCase(private val repository: Repository) {

    fun execute() = repository.listInventory

}