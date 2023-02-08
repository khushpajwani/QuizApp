package com.app.quizapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.quizapp.R
import com.app.quizapp.apicall.ApiServiceImpl
import com.app.quizapp.response.Result
import com.app.quizapp.utils.AllEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class QuizListViewModel(private val apiServiceImpl: ApiServiceImpl) : ViewModel() {

    val isNetworkAvailable = MutableLiveData<Boolean>()
    private val eventsChannel = Channel<AllEvents>()
    val allEventsFlow = eventsChannel.receiveAsFlow()

    private val _quizListData = MutableLiveData<ArrayList<Result>?>()
    val quizListResponse get() = _quizListData

    fun getQuizQuestions() {
        viewModelScope.launch {
            when {
                !isNetworkAvailable.value!! -> {
                    eventsChannel.send(AllEvents.StringResource(R.string.noInternet))
                }
                else -> {
                    eventsChannel.send(AllEvents.Loading(true))
                    apiServiceImpl.getQuizList().either({
                        eventsChannel.send(AllEvents.Loading(false))
                        eventsChannel.send(AllEvents.DynamicError(it))
                    }, {
                        eventsChannel.send(AllEvents.Loading(false))
                        quizListResponse.postValue(it.results)
                        eventsChannel.send(AllEvents.SuccessBool(true, 2))
                        eventsChannel.send(AllEvents.Success(it.results))
                    })
                }
            }
        }
    }
}