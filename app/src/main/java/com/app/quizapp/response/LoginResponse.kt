package com.app.quizapp.response

import kotlinx.serialization.Serializable


@Serializable
class LoginResponse (
    val token: String
)