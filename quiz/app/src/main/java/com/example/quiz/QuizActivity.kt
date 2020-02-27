package com.example.quiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    //class level variables
    var a1: Int = 0
    var a2: Int = 0
    var a3: Int = 0

    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //retrieve data from intent
        val firstname = intent.getStringExtra("EXTRA_FIRSTNAME")
        txtName.text = "Hello $firstname ${intent.getStringExtra("EXTRA_SURNAME")}"

        if (intent.getIntExtra("EXTRA_AGE", 0) < 11) {
            //hard coded for simplicity
            //repeat for other questions
            txtQ1.text = "2 + 3"
            a1 = 5
        } else {
            //repeat for other questions
            txtQ1.text = "23 + 36"
            a1 = 59
        }

    }

    fun back(x: View?) {
        //check answers
        if (!txtA1.text.isNullOrEmpty() && txtA1.text.toString().toInt() == a1)
            score++
        if (!txtA2.text.isNullOrEmpty() && txtA2.text.toString().toInt() == a2)
            score++
        if (!txtA3.text.isNullOrEmpty() && txtA3.text.toString().toInt() == a3)
            score++

        val scoreIntent: Intent = Intent().apply{
            putExtra("EXTRA_SCORE", score)
        }
        setResult(Activity.RESULT_OK, scoreIntent)


        finish()
    }

}
