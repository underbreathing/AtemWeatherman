package com.sheverdyaevartem.artemweatherman.data.api

import com.sheverdyaevartem.artemweatherman.data.dto.TemperatureResponse
import com.sheverdyaevartem.artemweatherman.presentation.viewmodel.TokenResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface MeteomaticsApi {

    companion object{
        const val TIME_FORMAT = "YYYY-MM-DD'T'HH:MM:SS.SSS'+03:00'"
    }

    @GET("api/v1/token")
    fun getToken(
        @Header("Authorization") authorization: String
    ): TokenResponse

    @GET("{datetime}/t_2m:C/{coordinates}/json?model=mix")
    fun getWeatherData(
        @Path("datetime") datetime: String,
        @Path("coordinates") coordinates: String,
    ):TemperatureResponse
}