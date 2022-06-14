package com.example.mathquizapp.model

import android.content.Context

class QuestionPool(context: Context) {
    private val database: DataBaseHelper = DataBaseHelper(context)
    private val allQuestions: ArrayList<Question> = database.getAllQuestions()

    //remind you to come back to that
    private val allAnswers = database.getAllAnswers()
    private val allQuestionCategories = database.getAllQuestionCategories()


    private fun getQuestionPool(qID: Int): List<Question> {
        val qList = ArrayList<Question>()

        allQuestions.forEach {
            if (qID == it.questionCategoryID)
                qList.add(it)
        }
        qList.shuffle()

        return qList.take(2)
    }

    fun gameQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        for (i in 1..database.getAllQuestionCategories().size)

                questionList.addAll(getQuestionPool(i))
        return questionList
    }

    fun gameAnswers(question: Question) =
        ArrayList(allAnswers.filter { it.questionNumber == question.questionNumber })
}
