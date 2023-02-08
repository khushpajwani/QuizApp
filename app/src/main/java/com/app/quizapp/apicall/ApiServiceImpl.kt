package com.app.quizapp.apicall

import com.app.quizapp.request.LoginRequestModel
import com.app.quizapp.request.UpdateUserRequest
import com.app.quizapp.response.*
import com.app.quizapp.utils.Either
import com.app.quizapp.utils.Failure
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.utils.io.charsets.*


class ApiServiceImpl(private val apiService: ApiService) : ApiServiceClass {

    override suspend fun login(loginRequestModel: LoginRequestModel): Either<String, LoginResponse> {
        return try {
            Either.Right(apiService.login(loginRequestModel))
        } catch (ex: Exception) {
            Either.Left(ex.errorMessage())
        }
    }

    override suspend fun getUserList(page:Int): Either<String, UsersListResponse> {
        return try {
            Either.Right(apiService.getUsers(page))
        } catch (ex: Exception) {
            Either.Left(ex.errorMessage())
        }
    }

    override suspend fun getQuizList(): Either<String, QuizData> {
        return try {
            Either.Right(apiService.getQuizData())
        } catch (ex: Exception) {
            Either.Left(ex.errorMessage())
        }
    }

    private suspend fun Exception.errorMessage() =
        when (this) {
            is ResponseException -> {
                response.readText(Charset.defaultCharset())
            }
            else -> {
                localizedMessage!!
            }
        }
}

fun Exception.toCustomExceptions() = when (this) {
    is ServerResponseException -> Failure.HttpErrorInternalServerError(this)
    is ClientRequestException ->
        when (this.response.status.value) {
            400 -> Failure.HttpErrorBadRequest(this)
            401 -> Failure.HttpErrorUnauthorized(this)
            403 -> Failure.HttpErrorForbidden(this)
            404 -> Failure.HttpErrorNotFound(this)
            405 -> Failure.MethodNotAllowed(this)
            else -> Failure.HttpError(this)
        }
    is RedirectResponseException -> Failure.HttpError(this)
    else -> Failure.GenericError(this)
}
