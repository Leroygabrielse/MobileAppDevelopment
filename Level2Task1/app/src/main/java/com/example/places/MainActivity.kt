/*
Applicatie Level 2 Task 1
Deze app laat de gebruiker een aantal landmarks zien met bijbehorende naam
Het is een scrollable applicatie

Gemaakt door: Leroy Gabriëlse
Student nr.: 500761062
e-mail: leroy.gabrielse@hva.nl

*/

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
