package com.sheverdyaevartem.artemweatherman.di

import com.sheverdyaevartem.artemweatherman.data.api.meteomatics.AuthApi
import com.sheverdyaevartem.artemweatherman.data.api.meteomatics.MeteomaticsApi
import com.sheverdyaevartem.artemweatherman.data.api.RemoteDataSource
import com.sheverdyaevartem.artemweatherman.data.api.freeweather.FreeWeatherApi

import com.sheverdyaevartem.artemweatherman.data.http_util.TokenInterceptor
import com.sheverdyaevartem.artemweatherman.data.impl.RemoteDataSourceImpl
import com.sheverdyaevartem.artemweatherman.data.mappers.TemperatureMapper
import com.sheverdyaevartem.artemweatherman.data.mappers.CityForecastMapper

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val METEOMATICS_BASE_URL = "https://api.meteomatics.com/"
private const val METEOMATICS_AUTH_URL = "https://login.meteomatics.com/"
private const val FREE_WEATHER_API_URL = "https://api.weatherapi.com/"


val dataModule = module {

    single<FreeWeatherApi> {
        Retrofit.Builder()
            .baseUrl(FREE_WEATHER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FreeWeatherApi::class.java)
    }

    single<MeteomaticsApi> {

        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(get()))
            .build()

        Retrofit.Builder()
            .baseUrl(METEOMATICS_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeteomaticsApi::class.java)
    }

    factory<AuthApi> {
        Retrofit.Builder()
            .baseUrl(METEOMATICS_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(get(), get())
    }


    factory {
        TemperatureMapper()
    }

    factory {
        CityForecastMapper()
    }
}