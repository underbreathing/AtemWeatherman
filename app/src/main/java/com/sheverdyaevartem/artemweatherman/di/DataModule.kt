package com.sheverdyaevartem.artemweatherman.di

import com.sheverdyaevartem.artemweatherman.data.api.MeteomaticsApi
import com.sheverdyaevartem.artemweatherman.data.api.RemoteDataSource

import com.sheverdyaevartem.artemweatherman.data.http_util.TokenInterceptor
import com.sheverdyaevartem.artemweatherman.data.impl.RemoteDataSourceImpl
import com.sheverdyaevartem.artemweatherman.data.mappers.TemperatureMapper

import okhttp3.OkHttpClient
import org.koin.dsl.factory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    val access_token =
        "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJ2IjoxLCJ1c2VyIjoiYXJ0ZW1mb3JlY2FzdGNvbV9mb3JlY2FzdGVyX2FydGVtZm9yZWNhc3QiLCJpc3MiOiJsb2dpbi5tZXRlb21hdGljcy5jb20iLCJleHAiOjE3MzU5MDgwMDMsInN1YiI6ImFjY2VzcyJ9.hTam_cet-q1jdEdyD96NFTg82HIIdfI2xCTQvPVzjIUZ41j-7KFEuVqPpyao3qkmN1hWJkhqDrah5n7A6dO1Iw"

    single<MeteomaticsApi> {

        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(access_token))
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.meteomatics.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeteomaticsApi::class.java)
    }
    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }


    factory{
        TemperatureMapper()
    }
}