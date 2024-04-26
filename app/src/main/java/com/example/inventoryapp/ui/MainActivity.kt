package com.example.inventoryapp.ui

import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventoryapp.R
import com.example.inventoryapp.data.Repository
import com.example.inventoryapp.data.model.InventoryEntity
import com.example.inventoryapp.databinding.ActivityMainBinding
import com.example.inventoryapp.ui.adapter.AdapterInventory
import com.example.inventoryapp.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository()
        viewModel = MainViewModel(repository)
        initRecyclerView()
        initView()
    }

    private fun initRecyclerView() {
        binding.rvInventory.layoutManager = LinearLayoutManager(this)
        val listInventory = viewModel.listInventory
        val adapter = AdapterInventory(listInventory) { item, view ->
            //open popup_menu
            val popup = PopupMenu(this, view)
            popup.inflate(R.menu.popup_item)
            popup.setOnMenuItemClickListener {
                onMenuOptionSelected(it, item)
                true

            }
            popup.show()
        }
        binding.rvInventory.adapter = adapter

    }

    fun editProduct(item: InventoryEntity) {
        showInputEditNameDialog(item.name) { name ->
            showInputEditPriceDialog(item.price.toString()) { price ->
                viewModel.editProduct(item.copy(name = name, price = price))
            }
        }
    }
    fun createProduct(){
        showInputNameDialog {name->
            showInputPriceDialog {price ->
                viewModel.createProduct(name, price)
            }
        }

    }

    private fun initView() {
        binding.fabAdd.setOnClickListener {
            createProduct()
        }
    }

    private fun showInputNameDialog(
        onAccepted: (name: String) -> Unit,
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ingresa el nombre del producto")
        val input = EditText(this)
        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            val text = input.text.toString()
            onAccepted(text)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showInputPriceDialog(
        onAccepted: (price: Double) -> Unit,
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ingresa el valo del producto")
        val input = EditText(this)
        input.setInputType(InputType.TYPE_CLASS_NUMBER)
        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            val text = input.text.toString()
            onAccepted(text.toDouble())
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showInputEditNameDialog(
        text: String,
        onEditNameDialog: (name: String) -> Unit,
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edite el nombre del producto")
        val input = EditText(this)
        input.setText(text)
        input.setInputType(InputType.TYPE_CLASS_TEXT)
        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            val text = input.text.toString()
            onEditNameDialog(text)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showInputEditPriceDialog(
        text: String,
        onAccepted: (price: Double) -> Unit,
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edite el valor del producto")
        val input = EditText(this)
        input.setText(text)
        input.setInputType(InputType.TYPE_CLASS_NUMBER)
        builder.setView(input)
        builder.setPositiveButton("OK") { _, _ ->
            val text = input.text.toString()
            onAccepted(text.toDouble())
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }


    private fun onMenuOptionSelected(menuItem: MenuItem,item: InventoryEntity) {
        when (menuItem.itemId) {
            R.id.menu_edit -> {
                editProduct(item)

                Toast.makeText(this, "editado", Toast.LENGTH_SHORT).show()
            }

            R.id.menu_delete -> {
                viewModel.deleteData(item.id)
                Toast.makeText(this, "eliminado", Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

}