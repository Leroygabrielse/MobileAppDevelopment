package com.example.boodschappenreminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val boodschappenLijst = arrayListOf<Product>()
    private val productAdapter = ProductAdapter(boodschappenLijst)
    private lateinit var productRepository : ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        productRepository = ProductRepository(this)
        initViews()


    }

    private fun initViews(){
        rvShoppingItems.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvShoppingItems.adapter = productAdapter
        rvShoppingItems.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvShoppingItems)
        getBoodschappenLijstFromDatabase()

        fab.setOnClickListener { addShoppingListItem() }
    }

    private fun addShoppingListItem(){
        if (validateFields()){
            CoroutineScope(Dispatchers.Main).launch {
                val product = Product(
                    naam = etProduct.text.toString(),
                    hoeveelheid = etHoeveelheid.text.toString().toInt()
                )
                withContext(Dispatchers.IO){
                    productRepository.insertProduct(product)
                }
                getBoodschappenLijstFromDatabase()

            }
        }
    }

    private fun getBoodschappenLijstFromDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            val shoppingList = withContext(Dispatchers.IO) {
                productRepository.getAllProducts()
            }
            this@MainActivity.boodschappenLijst.clear()
            this@MainActivity.boodschappenLijst.addAll(shoppingList)
            this@MainActivity.productAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteBoodschappenlijst(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                productRepository.deleteAllProducts()
            }
            getBoodschappenLijstFromDatabase()
        }
    }

    private fun validateFields(): Boolean {
        return if (etProduct.text.toString().isNotBlank() && etHoeveelheid.text.toString().isNotBlank()) {
            true
        } else {
            Toast.makeText(this, "Vul alle velden in!", Toast.LENGTH_SHORT).show()
            false
        }
    }


    private fun createItemTouchHelper() : ItemTouchHelper{

        val callback = object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val productToDelete = boodschappenLijst[position]
                CoroutineScope(Dispatchers.Main).launch { withContext(Dispatchers.IO){
                    productRepository.deleteProduct(productToDelete)
                }
                    getBoodschappenLijstFromDatabase()
                }

            }
        }

        return ItemTouchHelper(callback)
    }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_boodschappenlijst -> {
                deleteBoodschappenlijst()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
