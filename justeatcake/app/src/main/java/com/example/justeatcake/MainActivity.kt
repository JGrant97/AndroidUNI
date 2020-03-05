package com.example.justeatcake


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view -> showMap(view) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun showMap(view: View?) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(getString(R.string.justEatCakesLocation))
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_map -> {
                showMap(null)
                true
            }
            R.id.action_delivery -> {
                val deliveryIntent: Intent =
                    Intent(this, DeliveryActivity::class.java)
                startActivity(deliveryIntent)
                overridePendingTransition(R.anim.slide_in, R.anim.fade_out)
                true
            }
            R.id.action_delivery -> {
                Toast.makeText(this, "delivery", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_favourites -> {
                Toast.makeText(this, "favourites", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_contact -> {
                Toast.makeText(this, "contact", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun displayDelivery(view: View?) {
        //convert selected view to an ImageView using as
        val selectedImage = view as ImageView

        //construct message from the contentDescription of the selected view
        val message = getString(R.string.delivery_message,
            selectedImage.contentDescription)

        //launch explicit Intent with message
        val deliveryIntent: Intent =
            Intent(this, DeliveryActivity::class.java)
        deliveryIntent.putExtra("EXTRA_DELIVERY_MESSAGE", message)
        startActivity(deliveryIntent)
        overridePendingTransition(R.anim.slide_in, R.anim.fade_out)
    }

}
