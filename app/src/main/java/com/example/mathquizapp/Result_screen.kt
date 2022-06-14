package com.example.mathquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mathquizapp.model.DataBaseHelper
import com.example.mathquizapp.model.Student

import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import java.util.ArrayList;
import java.util.List;
import java.util.*

private val studentName = Main_screen.studentName
private val currentDate = Main_screen.currentDate
private val score = Question_screen.score

class Result_screen : AppCompatActivity() {
    private val db = DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_screen)

        val textScore = "Your Score ${score}/14"
        findViewById<TextView>(R.id.yourScore).text = textScore


        val pie = AnyChart.pie()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Correct", score))
        data.add(ValueDataEntry("Incorrect", 14 - score))

        pie.data(data)

        val anyChartView = findViewById<AnyChartView>(R.id.any_chart_view)
        anyChartView.setChart(pie)
    }

    fun buttonMainMenu (view: View) {
        val student = Student(-1, studentName, score, currentDate)

        val result = db.addStudent(student)
        when (result) {
            1 -> {
                Toast.makeText(this, "Submitting data", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Main_screen::class.java)
                startActivity(intent)
            }
            -1 -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }
}