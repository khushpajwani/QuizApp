package com.app.quizapp.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class LoginRequestModel(
    @SerialName("username")
    val email: String,
    @SerialName("password")
    val password: String
)