package com.example.inventoryapp.domain

import com.example.inventoryapp.data.Repository

class DeleteDataCaseUse(private val repository: Repository) {
    fun execute(id: Int) = repository.deleteData(id)
}