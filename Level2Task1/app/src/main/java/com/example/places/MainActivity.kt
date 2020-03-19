package com.example.places

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val places = arrayListOf<Places>()
    private val placeAdapter = PlaceAdapter(places)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews(){
        rvPlaces.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        rvPlaces.adapter = placeAdapter

        for (i in Places.PLACE_NAMES.indices) {
            places.add(Places(Places.PLACE_NAMES[i], Places.PLACE_RES_DRAWABLE_IDS[i]))
        }
        placeAdapter.notifyDataSetChanged()

    }
}
