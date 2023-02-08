package com.app.quizapp.utils

import java.util.regex.Pattern

val PASSWORD_PATTERN: Pattern = Pattern.compile("^(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")
const val KEY_TOKEN: String = "token"

object Screen {
}

object WidgetIds {
    const val TF_LOGIN_USERNAME_ID: String = "tf_login_username"
    const val TF_LOGIN_PASSWORD_ID: String = "tf_login_password"
}