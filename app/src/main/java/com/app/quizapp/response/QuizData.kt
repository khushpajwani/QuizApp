package com.app.quizapp.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuizData(
    @SerializedName("response_code") var response_code: Int,
    @SerializedName("result") var results: ArrayList<Result>
)