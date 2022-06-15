# Mobile-Development-Math-Quiz
This quiz application is designed for **_YEAR 1_** primary school students to use as an education mobile quiz app based on Android platform.

This project is to aim to demonstrate my ability to design and implement a Mobile Application consisting
of a set of Kotlin, using Android Studio and SQLite to build a suitable user interface, to implement
event handling procedures that provide a basis for an interactive and user-friendly app and adhere to
standard principles of the Model-View-Controller (MVC) design pattern and appropriately classes.

In the beginning, the app requires the student to insert the name, then select "**_start_**" button to begin
the math quiz.

<img src=images/beginning.png width="20%">

The app display 14 questions with multiple choice (4) for each question with **_ONE_** correct answer. These
questions are display randomly from the questions pool that contains 42 questions. The questions set
cover the 7 topics provided below. Two questions from each topic. 

* Number - number and place value
* Number - addition and subtraction
* Number - multiplication and division
* Number - fractions
* Measurement
* Geometry - properties of shapes
* Geometry - position and direction

<img src=images/questions.png width="20%">

When the student finishes the quiz by
selecting the "**_finish_**" button at the final question page, the app display the test grade by using AnyChart gradle to create an interactive 
charts for the result (credit to **ArsenyMalkov**, https://github.com/AnyChart/AnyChart-Android) . Also, the app will save the test result including the student entered details and 
data (student name, test date and grade).

<img src=images/result.png width="20%">

Student can save their record by selecting the "**_save_**" button, once the button is pressed, data will store to the SQLite database and will be
visable in the database.

<img src=images/SQLite.png width="50%">

