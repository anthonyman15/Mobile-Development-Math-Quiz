package com.example.mathquizapp.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.collections.ArrayList

/**
 * Database Name: Question.db
 */

private val DATABASE_NAME = "Question.db"
private val DATABASE_VERSION: Int = 1
private var studentList: ArrayList<Student> = ArrayList()
private var noOfStudent: Int = 0

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        //Questions Table
        const val Table_Name_Questions = "Questions"
        const val Question_Column_ID = "ID"
        const val Question_Category_ID = "QuestionCategoryID"
        const val Question_Number = "QuestionNumber"
        const val Question = "Question"

        //Question Category Table
        const val Table_Name_Category = "QuestionCategory"
        const val Question_Category_Column_ID = "ID"
        const val Question_Category = "QuestionCategory"

        //Answers Table
        const val Table_Name_Answers = "Answers"
        const val Answers_Column_ID = "ID"
        const val Answer = "Answer"
        const val IsCorrect = "IsCorrect"

        //Student Table
        const val Table_Name_Student = "Student"
        const val Student_Column_ID = "ID"
        const val Student_FirstName = "FirstName"
        const val Student_Score = "Score"
        const val Student_Date = "Date"

//        var currentDate = ""
//        var studentName = ""
//        var score = 0
    }


    override fun onCreate(db: SQLiteDatabase?) {
        //Create Questions Table
        try {

            var sqlCreateStatement: String =
                "CREATE TABLE " + Table_Name_Questions + " ( " +
                        Question_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Question_Category_ID + " INTEGER, " +
                        Question_Number + " INTEGER, " + Question + " TEXT " + ")"

            db?.execSQL(sqlCreateStatement)

            //Create Question Category Table
            sqlCreateStatement = "CREATE TABLE " + Table_Name_Category + " ( " +
                    Question_Category_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Question_Category_ID + " INTEGER, " + Question_Category + " TEXT " + ")"



            db?.execSQL(sqlCreateStatement)

            //Create Answers Table
            sqlCreateStatement = "CREATE TABLE " + Table_Name_Answers + " ( " +
                    Answers_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Question_Number + " INTEGER, " +
                    Answer + " TEXT, " + IsCorrect + "INTEGER " + ")"

            db?.execSQL(sqlCreateStatement)

            //Create Student Table
            sqlCreateStatement = "CREATE TABLE " + Table_Name_Student + " ( " +
                    Student_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Student_FirstName + " TEXT, " +
                    Student_Score + " INTEGER, " +
                    Student_Date + " TEXT " + ")"

            db?.execSQL(sqlCreateStatement)

        } catch (e: SQLException) {

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $Table_Name_Questions")
        db?.execSQL("DROP TABLE IF EXISTS $Table_Name_Category")
        db?.execSQL("DROP TABLE IF EXISTS $Table_Name_Answers")
        db?.execSQL("DROP TABLE IF EXISTS $Table_Name_Student")
    }

    //Get all questions
    fun getAllQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $Table_Name_Questions"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val questionID: Int = cursor.getInt(0)
                val questionCategoryID: Int = cursor.getInt(1)
                val questionNumber: Int = cursor.getInt(2)
                val question: String = cursor.getString(3)

                val q = Question(questionID, questionCategoryID, questionNumber, question)

                questionList.add(q)


            } while (cursor.moveToNext())

        cursor.close()
        db.close()
        questionList.shuffle()
        return questionList
    }

    //Get all categories
    fun getAllQuestionCategories(): ArrayList<Category> {
        val questionCategoryList = ArrayList<Category>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $Table_Name_Category"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val ID: Int = cursor.getInt(0)
                val questionCategoryID: Int = cursor.getInt(1)
                val questionCategory: String = cursor.getString(2)

                val c = Category(ID, questionCategoryID, questionCategory)

                questionCategoryList.add(c)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return questionCategoryList
    }

    //Get all answers
    fun getAllAnswers(): ArrayList<Answer> {
        val answerList = ArrayList<Answer>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $Table_Name_Answers"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val answerID: Int = cursor.getInt(0)
                val questionNumber: Int = cursor.getInt(1)
                val answer: String = cursor.getString(2)
                val isCorrect: Int = cursor.getInt(3)

                val a = Answer(answerID, questionNumber, answer, isCorrect)

                answerList.add(a)


            } while (cursor.moveToNext())

        cursor.close()
        db.close()
        answerList.shuffle()
        return answerList
    }

    //Get all students
//    fun getAllStudents(): ArrayList<Student> {
//        val studentList = ArrayList<Student>()
//        val db: SQLiteDatabase = this.readableDatabase
//        val sqlStatement = "SELECT * FROM $Table_Name_Student"
//
//        val cursor: Cursor = db.rawQuery(sqlStatement, null)
//
//        if (cursor.moveToFirst())
//            do {
//                val firstName: String = cursor.getString(1)
//                val score: Int = cursor.getInt(2)
//                val date: String = cursor.getString(3)
//
//                val s = Student(firstName, score, date)
//            } while (cursor.moveToNext())
//
//        cursor.close()
//        db.close()
//
//        return studentList
//    }

    //save student record
    fun addStudent(student: Student): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Student_FirstName, student.firstName)
        cv.put(Student_Score, student.score)
        cv.put(Student_Date, student.date)

        val success = db.insert(Table_Name_Student, null, cv)
        db.close()
        if (success.toInt() == -1)
            return success.toInt()
        else return 1
    }
}