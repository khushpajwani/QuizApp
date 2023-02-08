package com.app.quizapp.utils

import java.util.regex.Pattern

val PASSWORD_PATTERN: Pattern = Pattern.compile("^(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")
const val KEY_TOKEN: String = "token"

object Screen {
}