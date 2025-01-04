package com.sheverdyaevartem.artemweatherman.di

import com.sheverdyaevartem.artemweatherman.domain.interactors.ReceiptForecastInterceptor
import org.koin.dsl.module

val interactorsModule = module {

    factory {
        ReceiptForecastInterceptor(get())
    }
}