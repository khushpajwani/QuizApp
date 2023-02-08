package com.app.quizapp.request


@kotlinx.serialization.Serializable
data class UpdateUserRequest(val name: String, val job: String)
