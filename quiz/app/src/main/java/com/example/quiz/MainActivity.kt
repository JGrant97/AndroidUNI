package com.example.quiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val SCORE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startQuiz(x: View?) {
        //get data from input fields
        val firstname = if (txtFirstname.text.isNullOrEmpty()) ""
        else txtFirstname.text.toString()
        val surname = if (txtSurname.text.isNullOrEmpty()) ""
        else txtSurname.text.toString()
        val age = if (txtAge.text.isNullOrEmpty()) 0
        else txtAge.text.toString().toInt()

        val quizIntent: Intent = Intent(this, QuizActivity::class.java).apply{
            //put data in quizIntent
            putExtra("EXTRA_FIRSTNAME", firstname)
            putExtra("EXTRA_SURNAME", surname)
            putExtra("EXTRA_AGE", age)
        }

        startActivityForResult(quizIntent, SCORE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  scoreIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, scoreIntent)
        if (requestCode == SCORE_REQUEST &&
            resultCode == Activity.RESULT_OK) {
            txtResults.text =
                scoreIntent?.getIntExtra("EXTRA_SCORE", 0).toString()
        }
    }


}
