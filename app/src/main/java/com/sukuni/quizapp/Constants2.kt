package com.sukuni.quizapp

object Constants2 {
    // alle Konstanten, die gebraucht werden

    // Keys
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestion():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, "REACT",
            R.drawable.ic_rr, // Rückgbewert der Methode ist ein Int
            "onClick", "Click", "OnClick", "buttonClick",
            1
        )
        val que2 = Question(
            2, "REACT",
            R.drawable.ic_r2, // Rückgbewert der Methode ist ein Int
            "A programming language", "A JavaScript library for building user interfaces", "An operating system", "An IDE",
            2
        )
        val que3 = Question(
            3, "REACT",
            R.drawable.ic_r3, // Rückgbewert der Methode ist ein Int
            "changeState()", " updateState()", "setState()", "keepState()",
            3
        )
        val que4 = Question(
            4, "REACT",
            R.drawable.ic_r4, // Rückgabewert der Methode ist ein Int
            "[] =>", "()==>", "==>", "() =>",
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