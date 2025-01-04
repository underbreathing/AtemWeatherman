package com.sheverdyaevartem.artemweatherman.data.api.meteomatics

import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.TemperatureResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface MeteomaticsApi {

    companion object{
        const val TIME_FORMAT = "YYYY-MM-DD'T'HH:MM:SS.SSS'+03:00'"
    }

    @GET("{datetime}/t_2m:C/{coordinates}/json?model=mix")
    suspend fun getWeatherData(
        @Path("datetime") datetime: String,
        @Path("coordinates") coordinates: String,
    ): TemperatureResponse
}