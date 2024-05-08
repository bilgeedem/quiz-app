package com.sukuni.quizapp

import kotlin.random.Random

object Constants {
    // alle Konstanten, die gebraucht werden

    // Keys
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestion(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, "KOTLIN",
            R.drawable.ic_first, // Rückgbewert der Methode ist ein Int
            "fun main", "function main", "func", "function ",
            1
        )
        val que2 = Question(
            2, "KOTLIN",
            R.drawable.ic_second, // Rückgbewert der Methode ist ein Int
            "programming language for web development", "programming language for Android development", "A markup language ", "A database management system",
            2
        )
        val que3 = Question(
            3, "KOTLIN",
            R.drawable.ic_third, // Rückgbewert der Methode ist ein Int
            "variable", "var", "val", "let",
            3
        )
        val que4 = Question(
            4, "KOTLIN",
            R.drawable.ic_fourth, // Rückgabewert der Methode ist ein Int
            "call.myFunction()", "/myFunction", "myFunction;", "myFunction()",
            4
        )
        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)

        // Shuffle the questions list
        questionsList.shuffle()

        return questionsList
    }
}
