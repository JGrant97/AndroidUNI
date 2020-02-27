package com.example.notmyjob

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showOnMap(View: View?) {
        val location: String = txtShowOnMap.text.toString()
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:0,0?q=" + location)
        }

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("Not my job", "Can't handle maps")
        }

    }

    fun callNumber(View: View?) {
        val number: String = txtCallNumber.text.toString()
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:" + number)
        }

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("Not my job", "Can't handle calls")
        }

    }

    fun openWebsite(View: View?) {
        val url: String = txtOpenWebsite.text.toString()
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("Not my job", "Can't handle websites")
        }

    }

    fun shareText(View: View?) {
        val text: String = txtShareText.text.toString()
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }

        val chooseIntent = Intent.createChooser(intent, "How do you want to share?")
        if (chooseIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooseIntent)
        } else {
            Log.d("Not my job", "Can't handle sharing")
        }

    }

}
