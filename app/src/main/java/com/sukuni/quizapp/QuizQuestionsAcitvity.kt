package com.sukuni.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class QuizQuestionsAcitvity : AppCompatActivity(), View.OnClickListener {
    // Variablen initialisieren
    private var mCurrentPosition :Int = 1
    private var mQuestionList: ArrayList<Question>? =null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName:String? = null
    private var mCorrectAnswers: Int = 0

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions_acitvity)

        mUserName = intent.getStringExtra(Constants.USER_NAME) //String kommt von intent Z.36 MainActivity

        // Variablen mit UI Elementen verbinden
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)

        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        btnSubmit = findViewById(R.id.btn_submit)

        // Variablen mit anklickbar machen
        tvOptionOne?.setOnClickListener(this) // ist in der Class enthalten (View.OnClickListener)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionList = Constants.getQuestion() // Fragen als Liste
        setQuestion()
    }

    private fun setQuestion(){
        /*Log.i("QuestionsList size is ", "${questionslist.size}")
        // Logcat QuestionsList suchen, nachdem die App gestartet wurde

        for(i in questionslist){
            Log.e("Question", i.question)
        }*/
        // Zurücksetzen der Hintergründe der Antworten bei nächster Frage
        defaultOptionsView()

        val question: Question = mQuestionList!![mCurrentPosition -1] //!!-> ist nicht leer
        ivImage?.setImageResource(question.image)

        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptionOne?.text= question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

            if (mCurrentPosition == mQuestionList!!.size){
                // Wenn die letzte Frage erreicht ist
                btnSubmit?.text = "FINISH"
            }else {
                btnSubmit?.text = "SUBMIT"
            }
        }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>() // Festhalten der ausgewählten Position
        tvOptionOne?.let{
            options.add(0, it)
        }
        tvOptionTwo?.let{
            options.add(1, it)
        }
        tvOptionThree?.let{
            options.add(2, it)
        }
        tvOptionFour?.let{
            options.add(3, it)
        }
        // Standardaussehen für Optionen
        for(option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){ // Highlighting ausgewählte Option und

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43")) //ausgewählte Option mit farbigen Rand
        tv.setTypeface(tv.typeface, Typeface.BOLD) //ausgewählte Option in Fettdruck
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when(view?.id){         //Position der ausgewählten Option
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == 0){ // ist auf 0 initialisiert, also erste Frage
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{ // Belegung der Var, solange noch Fragen offen sind
                            setQuestion()
                        }
                        else -> {
                            if (mCorrectAnswers <= 2) {
                                val intent = Intent(this, ResultActivity::class.java) // Weiterleitung zur ResultActivity1
                                intent.putExtra(Constants.USER_NAME, mUserName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList?.size)
                                startActivity(intent)
                                finish() // Beenden der aktuellen Aktivität
                            } else {
                                val intent = Intent(this, ResultActivity2::class.java) // Weiterleitung zur ResultActivity2
                                intent.putExtra(Constants.USER_NAME, mUserName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList?.size)
                                startActivity(intent)
                                finish() // Beenden der aktuellen Aktivität
                            }

                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition -1)  // ist auf 1 initialisiert

                        if (question!!.correctAnswer != mSelectedOptionPosition){ // falsche Antwort
                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        }else { // richtige Antwort
                            mCorrectAnswers++
                        }

                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg) //richtige Antwort (wird immer eingefärbt)

                    if (mCurrentPosition == mQuestionList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text ="GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0  //Zurücksetzen der Pos Nr.
                }
            }

        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}