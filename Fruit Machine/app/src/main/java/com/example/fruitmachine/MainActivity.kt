package com.example.fruitmachine

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "MainActivity"

    //available credit
    private var credit: Int = 0

    //winnings accrued
    private var winnings: Int = 0

    //reel selections
    private var fruit1 = 0
    private var fruit2 = 0
    private var fruit3 = 0

    //fruit drawable assets
    private val fruits = listOf(
        R.drawable.apple,
        R.drawable.banana,
        R.drawable.bar,
        R.drawable.cherries,
        R.drawable.grapes,
        R.drawable.lemon,
        R.drawable.melon,
        R.drawable.orange
    )

    //val btnSpin = findViewById(R.id.btnSpin) as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            //restore state data
            credit = savedInstanceState.getInt("credit")
            txtCredits.text = "$credit"

            fruit1 = savedInstanceState.getInt("fruit1")
            imgSlotOne.setImageResource(fruits[fruit1])

            btnSpin.isEnabled = savedInstanceState.getBoolean("spinEnabled")
            btnCollect.isEnabled = savedInstanceState.getBoolean("collectEnabled")

        } else {
            //initialise variables
            credit = 0
            winnings = 0
            spinReels()

            //disable spin and collect buttons
            btnSpin.isEnabled = false
            btnCollect.isEnabled = false
        }

        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onCreate")


    }

    public override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        //activity is about to be restarted
    }

    override fun onDestroy() {
        super.onDestroy()
        //activity is about to be destroyed
    }

    override fun onStop() {
        super.onStop()
        //activity is no longer visible (it is now "stopped")
    }

    override fun onPause() {
        super.onPause()
        //another activity is taking focus
        //and this activity is about to be "paused"
    }

    override fun onResume() {
        super.onResume()
        //activity has become visible (it is now "resumed")
    }


    fun spinReels() {
        //pick 3 random fruits
        fruit1 = Random().nextInt(8)
        fruit2 = Random().nextInt(8)
        fruit3 = Random().nextInt(8)

        //display fruits on reels
        imgSlotOne.setImageResource(fruits[fruit1])
        imgSlotTwo.setImageResource(fruits[fruit2])
        imgSlotThree.setImageResource(fruits[fruit3])

        if (winnings == 0) {
            btnCollect.isEnabled = false
        }
        if (credit == 0) {
            btnSpin.isEnabled = false
        }

    }

    //addCredit event handler
    fun addCredit(x: View?) {
        //increase credit and display result
        credit++
        txtCredits.text = "$credit"

        btnSpin.isEnabled = true
    }

    //collect event handler
    fun collect(x: View?) {
        //show toast
        Toast.makeText(
            this, "You have taken $winnings!",
            Toast.LENGTH_LONG
        ).show()

        //decrease winnings to 0 and display
        winnings = 0
        txtWinnings.text = "$winnings"


        btnCollect.isEnabled = false

    }

    fun spin(x: View?) {
        //decrease credit as user has had a spin
        credit--

        //pick 3 random fruits and display on reels
        spinReels()

        //check for win
        if (fruit1 == fruit2 && fruit2 == fruit3) {
            winnings += 10
            btnCollect.isEnabled = true
        } else if (fruit2 == fruit3) {
            winnings += 5
            btnCollect.isEnabled = true
        }

        if (credit >= 1) {
            btnSpin.isEnabled = true
        }

        //display results
        txtWinnings.text = "$winnings"
        txtCredits.text = "$credit"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("credit", credit)

        outState.putInt("fruit1", fruit1)

        outState.putBoolean("spinEnabled", btnSpin.isEnabled)
    }


}
