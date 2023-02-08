package com.app.quizapp.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class CommonErrorResponse(
    var error: String? = null,
)
