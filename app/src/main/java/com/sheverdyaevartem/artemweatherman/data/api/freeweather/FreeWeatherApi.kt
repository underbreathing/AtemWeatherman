package com.sheverdyaevartem.artemweatherman.data.api.freeweather

import com.sheverdyaevartem.artemweatherman.data.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeWeatherApi {
    @GET("v1/current.json")
    suspend fun getTempInCity(@Query("q") city: String): WeatherResponse
}