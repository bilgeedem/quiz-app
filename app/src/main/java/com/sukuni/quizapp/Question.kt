package com.sukuni.quizapp

import android.media.Image
// data classes sind Datenmodelle

data class Question(
    val id: Int,
    val question: String,  // Frage
    val image: Int,  //Verweis auf Resource
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int  // Index der richtigen Antwort


)
