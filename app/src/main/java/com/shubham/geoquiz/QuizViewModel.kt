package com.shubham.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel:ViewModel() {
    init {
        Log.d(TAG,"ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"ViewModel about to be destroyed")
    }

    private var questionBank = listOf(
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true)

    )
    var currentIndex = 0

    val currentQuestionAnswer:Boolean
    get() = questionBank[currentIndex].answer

    val currentQuestionText:Int
    get() = questionBank[currentIndex].txtResId

    val questionBankSize:Int
    get() = questionBank.size

    fun moveToNext(){
        currentIndex = (currentIndex+1)%questionBank.size
    }
    fun moveToLast(){
        currentIndex = questionBank.size-1
    }
    fun moveToPrev(){
        currentIndex = (currentIndex -1)%questionBank.size
    }
}