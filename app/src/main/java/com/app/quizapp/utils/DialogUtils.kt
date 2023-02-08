package com.app.quizapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.LinearLayout
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.quizapp.databinding.ProgressDialogBinding


object DialogUtils {

    var progressBar: AlertDialog? = null

    fun showProgressBar(activity: Activity) {
        val dialogBuilder = AlertDialog.Builder(activity)
        val bindingDialog: ProgressDialogBinding =
            ProgressDialogBinding.inflate(activity.layoutInflater)
        dialogBuilder.setView(bindingDialog.root)
        dialogBuilder.setCancelable(false)
        val alertDialog = dialogBuilder.create()
        progressBar = alertDialog
        val window = alertDialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        alertDialog.show()
    }

    fun hideProgressBar() {
        if (progressBar != null && progressBar?.isShowing!!) {
            progressBar?.dismiss()
        }
    }
}

@Composable
fun circularProgressBar(alpha: Float) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp).alpha(alpha),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = Dp(value = 4F)
        )
    }
}