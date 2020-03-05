package com.example.justeatcake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delivery.*


class DeliveryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        //extract message and display in Instructions TextView
        txtDeliveryInstruction.text =
            intent.getStringExtra("EXTRA_DELIVERY_MESSAGE")
    }

    fun onRadioButtonClicked (view: View?){
        //convert selected view to a RadioButton using as
        val selectedButton = view as RadioButton

        //if radio button is checked, display toast
        if (selectedButton.isChecked) {
            val message = getString(R.string.delivery_method,
                selectedButton.text)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out)
        }
    }


}

