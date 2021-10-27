package com.example.quizapp.Utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Question(val text : String,var  answers : MutableList<Answer>, val type:QuestionType)

data class Answer(val answer:String,val value:Boolean)
enum class QuestionType {
    ONE_CORRECT,
    MANY_CORRECT,
    TRUE_OR_FALSE,
    SHORT_ANSWER
}
class SharedViewModel : ViewModel(){
    var score = MutableLiveData(0.0)
    var numOfQuestions = MutableLiveData(0)
    var currentQuestion : Question? = null

    fun result(score:Double){
        this.score.value = score
    }
    fun numOfQuestions(num:Int){
        this.numOfQuestions.value = num
    }
}

