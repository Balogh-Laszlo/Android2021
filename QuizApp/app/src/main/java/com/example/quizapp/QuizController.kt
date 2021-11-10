package com.example.quizapp

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.Model.SinglePost
import com.example.quizapp.Repository.Repository
import com.example.quizapp.Utils.QuestionType
import com.example.quizapp.Utils.Answer
import com.example.quizapp.Utils.Question
import com.example.quizapp.Utils.SharedViewModel


class QuizController {
    companion object {
        var listOfQuestions: MutableList<Question> = mutableListOf()
        var listOfRawQuestions: MutableList<SinglePost> = mutableListOf()
    }
    init {
        if(listOfRawQuestions.size>0){
            listOfRawQuestions.forEach { rawQuestion ->
                val answers :MutableList<Answer> = mutableListOf()
                answers.add(Answer(rawQuestion.correct_answer,true))
                rawQuestion.incorrect_answers.forEach { answer->
                    answers.add(Answer(answer,false))
                }
                listOfQuestions.add(Question(rawQuestion.question,answers,QuestionType.ONE_CORRECT,rawQuestion.difficulty,rawQuestion.category))
            }
        }



    }


    fun randomizeQuestions(){
        listOfQuestions.shuffle()
        listOfQuestions.forEach { it.answers.shuffle() }
    }
    fun getQuestion(questionIndex: Int):Question{
        return listOfQuestions[questionIndex]
    }

    fun size():Int = 10
}