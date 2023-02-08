package com.app.quizapp.di

import android.app.Application
import com.app.quizapp.apicall.ApiService
import com.app.quizapp.apicall.ApiServiceImpl
import com.app.quizapp.viewmodel.LoginViewModel
import com.app.quizapp.viewmodel.QuizListViewModel
import org.koin.dsl.module

val appModule = module {
    single { provideApiService() }
    single { provideApiServiceImpl(get()) }
    factory { LoginViewModel(get()) }
    factory { QuizListViewModel(get()) }
}

fun provideApiService() = ApiService(ktorHttpClient)
fun provideApiServiceImpl(apiService: ApiService) = ApiServiceImpl(apiService)
fun provideViewModel(apiService: ApiServiceImpl, context: Application) = LoginViewModel(apiService)
fun provideQuizViewModel(apiService: ApiServiceImpl, context: Application) = QuizListViewModel(apiService)
