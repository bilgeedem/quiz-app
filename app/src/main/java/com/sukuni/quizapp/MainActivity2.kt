package com.sukuni.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val button1 : Button = findViewById(R.id.button1)

        val button2 : Button = findViewById(R.id.button2)


        button1.setOnClickListener {


                // Intent: Kommunikation zwischen verschiedenen Komponenten einer Anwendung oder zwischen verschiedenen Anwendungen
                val intent = Intent(this, QuizQuestionsAcitvity::class.java)

                startActivity(intent) //öffnet neuen Screen
                finish() // schließt den Screen von zuvor
            }







        button2.setOnClickListener {


            // Intent: Kommunikation zwischen verschiedenen Komponenten einer Anwendung oder zwischen verschiedenen Anwendungen
            val intent = Intent(this, QuizQuestionsAcitvity2::class.java)

            startActivity(intent) //öffnet neuen Screen
            finish() // schließt den Screen von zuvor
        }











    }






    }
