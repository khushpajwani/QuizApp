package com.app.quizapp.apicall

import com.app.quizapp.request.LoginRequestModel
import com.app.quizapp.response.LoginResponse
import com.app.quizapp.response.QuizData
import com.app.quizapp.response.UsersListResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class ApiService(private val client: HttpClient) {

    suspend fun getUsers(page: Int): UsersListResponse =
        client.get {
            url(ApiRoutes.USERS)
            parameter("page", page)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    suspend fun getQuizData(): QuizData =
        client.get {
            url(ApiRoutes.QUIZ_URL)
            parameter("amount", 5)
            parameter("difficulty", "medium")
            parameter("type", "multiple")
            parameter("category", 11)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    suspend fun login(loginRequestModel: LoginRequestModel): LoginResponse =
        client.post {
            url(ApiRoutes.LOGIN)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            body = loginRequestModel
        }
}