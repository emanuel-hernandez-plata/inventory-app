package com.example.inventoryapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inventoryapp.data.Repository
import com.example.inventoryapp.data.model.InventoryEntity
import com.example.inventoryapp.domain.CreateProductUseCase
import com.example.inventoryapp.domain.DeleteDataCaseUse
import com.example.inventoryapp.domain.EdithProductCaseUse
import com.example.inventoryapp.domain.GetDataUseCase
import com.example.inventoryapp.domain.LoadDataUseCase

class MainViewModel(repository: Repository) : ViewModel() {

    private val deleteDataCaseUse = DeleteDataCaseUse(repository)
    private val getDataUseCase = GetDataUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val createProduct = CreateProductUseCase(repository)
    private val editProduct = EdithProductCaseUse(repository)

    private var _listInventory = MutableLiveData<List<InventoryEntity>>()
    val listInventory get() = _listInventory

    init {
        _listInventory = getDataUseCase.execute()
        loadDataUseCase.execute()
    }

    fun deleteData(id: Int) = deleteDataCaseUse.execute(id)

    fun createProduct(name: String, price: Double) = createProduct.execute(name, price)

    fun editProduct(inventoryEntity: InventoryEntity) = editProduct.execute(inventoryEntity)
}
