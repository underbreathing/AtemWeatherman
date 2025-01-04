package com.sheverdyaevartem.artemweatherman.di

import com.sheverdyaevartem.artemweatherman.presentation.viewmodel.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory {
        MainViewModel(get())
    }
}