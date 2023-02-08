package com.app.quizapp.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerializedName("category") var category: String,
    @SerializedName("correct_answer") var correct_answer: String,
    @SerializedName("difficulty") var difficulty: String,
    @SerializedName("incorrect_answers") var incorrect_answers: List<String>,
    @SerializedName("question") var question: String,
    @SerializedName("type") var type: String
)