/*
Applicatie Level 3 Task 2
Deze app laat de gebruiker tabbladen van websites toevoegen aan het hoofdmenu. Als men klikt op
het tabblad dan gaat men naar de website via in-app browser

Gemaakt door: Leroy Gabriëlse
Student nr.: 500761062
e-mail: leroy.gabrielse@hva.nl

*/

package com.example.studentportal

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val PORTAL_REQUEST_CODE = 100

class PortalsActivity : AppCompatActivity() {

    private var customTabHelper: CustomTabHelper = CustomTabHelper()

    companion object {
        private const val MINDORKS_PUBLICATION = "https://blog.mindorks.com/understanding-density-independent-pixel-sp-dp-dip-in-android"
    }


    private val portals = arrayListOf<Portal>()

    //adapter with the array list and click function
    private val portalAdapter = PortalAdapter(portals, {portals : Portal -> portalItemClicked(portals)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Function which is executed when returning from AddPortalActivity
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PORTAL_REQUEST_CODE -> {
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal) //Add data to the array list
                    portalAdapter.notifyDataSetChanged() //update
                }
            }
        }

    }
    //initialize view. set layoutmanager and adapter plus add function to button
    private fun initViews(){

        rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        rvPortals.adapter = portalAdapter
        fab.setOnClickListener {
            startAddPortalsActivity()
        }
    }

    //Start AddPortalsActivity
    private fun startAddPortalsActivity(){
        val intent = Intent(this, AddPortalsActivity::class.java)
        startActivityForResult(intent, PORTAL_REQUEST_CODE)
    }

    //RecyclerView item click function
    private fun portalItemClicked(portals: Portal){

        val builder = CustomTabsIntent.Builder()

        // modify toolbar color
        builder.setToolbarColor(ContextCompat.getColor(this@PortalsActivity, R.color.colorPrimary))

        // add share button to overflow menu
        builder.addDefaultShareMenuItem()

        val anotherCustomTab = CustomTabsIntent.Builder().build()

        val requestCode = 100
        val intent = anotherCustomTab.intent
        intent.setData(Uri.parse(portals.PortalUrl))

        val pendingIntent = PendingIntent.getActivity(this,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        // add menu item to oveflow
        builder.addMenuItem("Sample item", pendingIntent)

        // menu item icon
        // val bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)
        // builder.setActionButton(bitmap, "Android", pendingIntent, true)

        // modify back button icon
        // builder.setCloseButtonIcon(bitmap)

        //show website title
        builder.setShowTitle(true)

        // animation for enter and exit of tab
        builder.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
        builder.setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)

        val customTabsIntent = builder.build()

        // check is chrom available
        val packageName = customTabHelper.getPackageNameToUse(this, portals.PortalUrl)


        customTabsIntent.intent.setPackage(packageName)
        customTabsIntent.launchUrl(this, Uri.parse(portals.PortalUrl))
    }

}
