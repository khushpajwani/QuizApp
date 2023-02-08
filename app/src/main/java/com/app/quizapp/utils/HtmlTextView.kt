package com.app.quizapp.utils

import android.content.Context
import androidx.core.text.HtmlCompat


class HtmlTextView(context: Context) : androidx.appcompat.widget.AppCompatTextView(context) {
    private fun init() {
        text = HtmlCompat.fromHtml(text.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}