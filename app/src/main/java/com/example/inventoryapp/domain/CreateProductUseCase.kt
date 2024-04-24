package com.example.inventoryapp.domain

import com.example.inventoryapp.data.Repository

class CreateProductUseCase(private val repository: Repository) {
    fun execute(name: String, price: Double) = repository.createProduct(name, price)
}