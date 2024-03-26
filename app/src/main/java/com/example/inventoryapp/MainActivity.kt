package com.example.inventoryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventoryapp.adapter.AdapterInventory
import com.example.inventoryapp.databinding.ActivityMainBinding
import com.example.inventoryapp.model.InventoryEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.rvInventory.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterInventory(getListInventory())
        binding.rvInventory.adapter = adapter
    }

    private fun getListInventory() = listOf(
        InventoryEntity(id= 1, image = 3, name = "awa", price = 6000.0 ),
        InventoryEntity(id= 2, image = 3, name = "lentes de contacto", price = 6500.0 ),
        InventoryEntity(id= 3, image = 3, name = "arepa mixta", price = 8500.0 ),
        InventoryEntity(id= 4, image = 5, name = "caguama", price = 10000.0 ),
    )
}