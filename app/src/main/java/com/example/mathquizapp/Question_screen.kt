package com.example.mathquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mathquizapp.model.*

class Question_screen : AppCompatActivity() {

    lateinit var databasePool: QuestionPool
    lateinit var dbHelper: DataBaseHelper
    lateinit var studentQuestion: ArrayList<Question>
    lateinit var studentAnswers: ArrayList<Answer>

    //lateinit var studentRecords: ArrayList<Student>
    lateinit var textViewLabel: TextView
    lateinit var questionCounter: TextView
    lateinit var scoreLabel: TextView
    lateinit var buttonNext: Button

    lateinit var currentQuestion: Question
    lateinit var radioButtons: Array<RadioButton>
    lateinit var radioGroup: RadioGroup

    var currentCounter = 0

    companion object {
        var score = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_screen)

        //Configurations
        databasePool = QuestionPool(this)
        studentQuestion = databasePool.gameQuestions()
        textViewLabel = findViewById(R.id.textViewQuestion)
        questionCounter = findViewById<TextView>(R.id.textViewQuestionCount)
        scoreLabel = findViewById<TextView>(R.id.textViewScore)
        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        score = 0

        findViewById<TextView>(R.id.textViewEnteredName).text = Main_screen.studentName
        findViewById<TextView>(R.id.textViewCurrentTestDate).text = Main_screen.currentDate

        buttonNext = findViewById(R.id.buttonNext)

        buttonNext.setOnClickListener() {
            nextClick()
        }

        radioButtons = arrayOf(
            findViewById(R.id.radio_one),
            findViewById(R.id.radio_two),
            findViewById(R.id.radio_three),
            findViewById(R.id.radio_four),
            findViewById(R.id.radio_five)
        )
        nextQuestion()
    }

    fun nextQuestion() {
        currentCounter = currentCounter.coerceAtMost(13)
        val correctAnswer: Answer? =
            if (this::studentAnswers.isInitialized) studentAnswers.filter { it.isCorrect == 1 }[0] else null
        currentQuestion = studentQuestion[currentCounter]
        currentCounter++
        studentAnswers = databasePool.gameAnswers(currentQuestion)
        textViewLabel.text = currentQuestion.question
        questionCounter.text = "Question: $currentCounter/${studentQuestion.size}"
        var i = 0
        radioButtons.forEach {
            if (it.text == correctAnswer?.answer && it.isChecked) {
                score++
                scoreLabel.text = "Score: $score"
            }
            it.text = studentAnswers[i].answer
            i++
        }
//        checkScore() //new added
        radioGroup.clearCheck()
        if (correctAnswer != null)
            Toast.makeText(
                applicationContext,
                "The Correct Answer is ${correctAnswer?.answer}",
                Toast.LENGTH_SHORT
            ).show()
    }

    fun nextClick() {
        if (currentCounter < studentQuestion.size) {
            if (currentCounter == studentQuestion.size - 1) {
                buttonNext.text = "Finish"
            }
            nextQuestion()
        } else {
            nextQuestion()
            finishQuestion()
        }
    }

    //finish screen
    fun finishQuestion() {
        val intent = Intent(this, Result_screen::class.java)
        startActivity(intent)
    }

}
