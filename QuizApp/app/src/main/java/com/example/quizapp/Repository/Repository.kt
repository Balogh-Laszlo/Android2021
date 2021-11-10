package com.example.quizapp.Repository

import com.example.quizapp.Model.Post
import com.example.quizapp.api.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getPost() : Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}