package com.app.quizapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
fun circularProgressBar(showDialog: Boolean) {
    var dialog by remember { mutableStateOf(showDialog) }
    if (dialog) {
        Dialog(
            onDismissRequest = { dialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment= Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(androidx.compose.ui.graphics.Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
}