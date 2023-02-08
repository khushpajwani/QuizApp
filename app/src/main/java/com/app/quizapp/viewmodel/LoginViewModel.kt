package com.app.quizapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quizapp.R
import com.app.quizapp.apicall.ApiServiceImpl
import com.app.quizapp.request.LoginRequestModel
import com.app.quizapp.response.LoginResponse
import com.app.quizapp.utils.AllEvents
import com.app.quizapp.utils.validatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(private val apiServiceImpl: ApiServiceImpl) : ViewModel() {

    val isNetworkAvailable = MutableLiveData<Boolean>()
    var userName = MutableStateFlow<String>("")
    var password = MutableStateFlow<String>("")
    private val eventsChannel = Channel<AllEvents>()
    val allEventsFlow = eventsChannel.receiveAsFlow()

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse get() = _loginResponse

    fun login() {

        val userNameString: StateFlow<String> = userName
        val passString: StateFlow<String> = password
        viewModelScope.launch {
            when {
                !isNetworkAvailable.value!! -> {
                    eventsChannel.send(AllEvents.StringResource(R.string.noInternet))
                }
                userNameString.value.isBlank() -> {
                    eventsChannel.send(
                        AllEvents.StringResource(R.string.enter_username, null)
                    )
                }
                passString.value.isBlank() -> {
                    eventsChannel.send(AllEvents.StringResource(R.string.error_password_empty))
                }
                passString.value.validatePassword() -> {
                    eventsChannel.send(AllEvents.StringResource(R.string.error_password_invalid))
                }
                else -> {
                    eventsChannel.send(AllEvents.Loading(true))
                    apiServiceImpl.login(LoginRequestModel(userNameString.value, passString.value))
                        .either(
                            {
                                eventsChannel.send(AllEvents.Loading(false))
                                eventsChannel.send(AllEvents.DynamicError(it))
                            },
                            {
                                eventsChannel.send(AllEvents.Loading(false))
                                loginResponse.postValue(it)
                                eventsChannel.send(AllEvents.Success(it))
                                eventsChannel.send(AllEvents.SuccessBool(true, 1))
                            })
                }
            }
        }
    }
}