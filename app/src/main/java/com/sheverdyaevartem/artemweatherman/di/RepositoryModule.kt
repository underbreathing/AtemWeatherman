package com.sheverdyaevartem.artemweatherman.di

import com.sheverdyaevartem.artemweatherman.data.impl.WeatherRepositoryImpl
import com.sheverdyaevartem.artemweatherman.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<WeatherRepository> {
        WeatherRepositoryImpl(get(),get(),get())
    }
}