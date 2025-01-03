package com.sheverdyaevartem.artemweatherman.di

import com.sheverdyaevartem.artemweatherman.domain.interactors.ReceiptTemperatureUseCase
import org.koin.dsl.module

val interactorsModule = module {

    factory {
        ReceiptTemperatureUseCase(get())
    }
}