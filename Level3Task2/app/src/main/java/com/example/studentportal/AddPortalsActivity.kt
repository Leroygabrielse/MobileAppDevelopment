package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_portals.*
import kotlinx.android.synthetic.main.item_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class AddPortalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portals)

        val editText = findViewById(R.id.etUrl) as EditText
        editText.setSelection(editText.text.length)

        initViews()
    }

    //Initialize views
    private fun initViews(){

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Portal"

        //Set cursor to end of editText van de Url om te beginnen met typen achter http://
        val editText = findViewById(R.id.etUrl) as EditText
        editText.setSelection(editText.text.length)

        btnAddPortal.setOnClickListener { onSaveClick() }
    }

    //Function when button is pressed in AddPortalsActivity
    private fun onSaveClick(){
        if ((etPortalName.text.toString().isNotBlank()) and etUrl.text.toString().isNotBlank()){
            val portal = Portal(etPortalName.text.toString(),etUrl.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL,portal)
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        }
        else{
            Log.d("Test", "Reached right place")
            Snackbar.make(btnAddPortal,"Fill in all fields!",Snackbar.LENGTH_LONG).show()
        }
    }

    //Function to let the back button work
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
