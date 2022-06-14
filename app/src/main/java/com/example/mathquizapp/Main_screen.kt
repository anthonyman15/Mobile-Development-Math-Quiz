package com.example.mathquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mathquizapp.model.DataBaseHelper
import java.text.SimpleDateFormat
import java.util.*

class Main_screen : AppCompatActivity() {
    companion object {
        var studentName = ""
        var currentDate = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
        setContentView(R.layout.main_screen)

        val button = findViewById<Button>(R.id.buttonStart)
        val editText = findViewById<EditText>(R.id.editTextEnterName)
        val textViewDate = findViewById<TextView>(R.id.textViewDate)

        //declare current date
        val calendar = Calendar.getInstance()
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd LLLL yyyy")
        currentDate = simpleDateFormat.format(calendar.time).toString()
        textViewDate.text = currentDate
        button.setOnClickListener {
            if (editText.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Please enter your name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                studentName = editText.text.toString()
                openQuestion_screen()
            }
        }
    }

    fun openQuestion_screen() {
        val intent = Intent(this, Question_screen::class.java)
        startActivity(intent)
    }
}