package com.example.quizapp

import com.example.quizapp.Utils.QuestionType
import com.example.quizapp.Utils.Answer
import com.example.quizapp.Utils.Question



class QuizController {
    companion object {
        val listOfQuestions: MutableList<Question> = mutableListOf(
            Question(
                "What is the difference between the variable declaration with var and val?",
                mutableListOf(
                    Answer("val can't be modified var can", true),
                    Answer("val variables can't be reached outside the class var can", false),
                    Answer("val is dynamic declaration var is static", false)
                ), QuestionType.ONE_CORRECT
            ),
            Question(
                "What is Elvis operator in Kotlin?",
                mutableListOf(
                    Answer("an emoji", false),
                    Answer("a way we can draw Elvis on the layout", false),
                    Answer(
                        "an operator which is used for describe what should do the code if the variable is null",
                        true
                    ),
                    Answer(
                        "it is like an if statement, represent a decision-making statement",
                        true
                    )
                ), QuestionType.MANY_CORRECT
            ),
            Question(
                "With which key word can we create a singleton class?",
                mutableListOf(Answer("Object", true), Answer("object", true)),
                QuestionType.SHORT_ANSWER
            )
        )
    }


    fun randomizeQuestions(){
        listOfQuestions.shuffle()
        listOfQuestions.forEach { it.answers.shuffle() }
    }
    fun getQuestion(questionIndex: Int):Question{
        return listOfQuestions[questionIndex]
    }

    fun size():Int = listOfQuestions.size
}