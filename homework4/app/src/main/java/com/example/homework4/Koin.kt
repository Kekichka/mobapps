package com.example.homework4

import com.example.homework4.model.PhoneCheckRepo
import com.example.homework4.model.Retrofit
import com.example.homework4.viewModel.PhoneCheckViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Retrofit.create() }
    single { PhoneCheckRepo(get()) }
    viewModel { PhoneCheckViewModel(get()) }
}