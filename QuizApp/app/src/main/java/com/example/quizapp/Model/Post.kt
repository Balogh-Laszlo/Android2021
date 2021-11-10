package com.example.quizapp.Model

data class SinglePost(
    val category : String,
    val type: String,
    val difficulty:String,
    val question:String,
    val correct_answer:String,
    val incorrect_answers:MutableList<String>
)
data class Post(
    val response_code: Int,
    val results: MutableList<SinglePost>
)

