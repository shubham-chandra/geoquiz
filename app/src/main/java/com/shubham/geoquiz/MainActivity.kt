package com.shubham.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const  val  TAG = "MainActivity"
private  const val KEY_INDEX = "index"
class MainActivity : AppCompatActivity() {

    private lateinit var trueButton:Button
    private  lateinit var falseButton:Button
    private  lateinit var questionTextView: TextView
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private var trueButtonClicked:Boolean = false
    private var falseButtonClicked:Boolean = false


    private var quesionAnswered = arrayListOf<Int>(
        0,
        0,
        0,
        0,
        0
    )
    private var score = 0

    private val quizViewModel:QuizViewModel by lazy{
        //ViewModelProviders.of(this).get(QuizViewModel::class.java)
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX,0)?:0
        quizViewModel.currentIndex = currentIndex

        Log.d(TAG,"OnCreate called")

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        questionTextView = findViewById(R.id.question_text_view)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.previous_button)


        //val provider:ViewModelProvider = ViewModelProviders.of(this)
        //val quizViewModel = provider.get(QuizViewModel::class.java)
        //Log.d(TAG,"Got a Quiz View Model $quizViewModel")

        trueButton.setOnClickListener { view:View ->
            //Do something for True button
            if(!trueButtonClicked){
                checkAnswer(true)
                trueButtonClicked = true
                falseButtonClicked = true
                checkScore()


            }
         }

        falseButton.setOnClickListener { view:View->
            //Do something for false button
            if (!falseButtonClicked){
                checkAnswer(false)
                falseButtonClicked =true
                trueButtonClicked = true
                checkScore()
            }

         }


        nextButton.setOnClickListener { view:View ->
           quizViewModel.moveToNext()
           updateQuestion()

         }

        prevButton.setOnClickListener {
            var currentIndex = quizViewModel.currentIndex
            if (currentIndex == 0){
                quizViewModel.moveToLast()
            }else{quizViewModel.moveToPrev()}
            updateQuestion()
        }

        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()

        }

        updateQuestion()
    }

    private fun checkScore(){
        if(quesionAnswered.sum()==quesionAnswered.size){
            val percentage = (score/quesionAnswered.size.toDouble())*100
            val msg = percentage.toString()
            Toast.makeText(this,"Score is $msg%",Toast.LENGTH_SHORT).show()
            score = 0
            quesionAnswered = arrayListOf<Int>( 0,
                0,
                0,
                0,
                0)
        }
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        updateButtons()
    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val msgTextId:Int;
        if (userAnswer == correctAnswer){
            msgTextId = R.string.correct_toast
            score += 1
        }else{
            msgTextId = R.string.incorrect_toast
        }
        quesionAnswered[quizViewModel.currentIndex]=1
        Toast.makeText(this,msgTextId,Toast.LENGTH_SHORT).show()
    }

    private fun updateButtons(){
         trueButtonClicked = false
         falseButtonClicked = false
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"OnStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"OnResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"OnPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"OnStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
    Log.d(TAG,"OnDestroy called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,"OnSave instance called")
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }
}