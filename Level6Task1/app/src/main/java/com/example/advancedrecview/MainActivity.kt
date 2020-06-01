package com.example.advancedrecview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val colors = arrayListOf<ColorItem>()
    private val colorAdapter = ColorItemAdapter(colors) {colorItem -> onColorCLick(colorItem)}
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()

    }
    private fun initViews(){
        rvColors.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvColors.adapter = colorAdapter
    }
    private fun onColorCLick(colorItem: ColorItem){
        Snackbar.make(rvColors, "You clicked on the color: ${colorItem.name}", Snackbar.LENGTH_LONG).show()
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.colorItems.observe(this, Observer {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        })
    }
}
