package com.app.quizapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.app.quizapp.utils.ConnectionLiveData
import com.app.quizapp.utils.isConnectedToInternet
import com.app.quizapp.viewmodel.LoginViewModel
import com.app.quizapp.viewmodel.QuizListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

open class BaseActivity : ComponentActivity() {

    private var connectionLiveData: ConnectionLiveData? = null
    val loginViewModel: LoginViewModel by viewModel()
    val quizViewModel: QuizListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = ConnectionLiveData(this)
        loginViewModel.isNetworkAvailable.value = isConnectedToInternet()
        quizViewModel.isNetworkAvailable.value = isConnectedToInternet()
    }
}
