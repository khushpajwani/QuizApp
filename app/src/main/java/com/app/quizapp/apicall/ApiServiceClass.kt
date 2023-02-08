package com.app.quizapp.apicall

import com.app.quizapp.request.LoginRequestModel
import com.app.quizapp.response.LoginResponse
import com.app.quizapp.response.QuizData
import com.app.quizapp.response.UsersListResponse
import com.app.quizapp.utils.Either


interface ApiServiceClass {
    suspend fun login(loginRequestModel: LoginRequestModel): Either<String, LoginResponse>
    suspend fun getUserList(page:Int): Either<String, UsersListResponse>
    suspend fun getQuizList(): Either<String, QuizData>
}