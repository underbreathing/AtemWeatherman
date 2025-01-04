package com.sheverdyaevartem.artemweatherman.data.impl

import android.util.Log
import com.sheverdyaevartem.artemweatherman.data.api.meteomatics.MeteomaticsApi
import com.sheverdyaevartem.artemweatherman.data.api.RemoteDataSource
import com.sheverdyaevartem.artemweatherman.data.api.freeweather.FreeWeatherApi
import com.sheverdyaevartem.artemweatherman.data.dto.FreeWeatherRequest
import com.sheverdyaevartem.artemweatherman.data.dto.NetworkResponse
import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.TemperatureRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val weatherService: MeteomaticsApi,
    private val freeWeatherService: FreeWeatherApi
) : RemoteDataSource {

    override suspend fun doRequest(dto: Any): NetworkResponse {
        return withContext(Dispatchers.IO) {
            try {
                when (dto) {
                    is TemperatureRequest -> {
                        weatherService.getWeatherData(dto.currentTime, dto.coordinates)
                            .apply { resultCode = 200 }
                    }

                    is FreeWeatherRequest -> {
                        freeWeatherService.getTempInCity(dto.city).apply { resultCode = 200 }
                    }

                    else -> {
                        NetworkResponse().apply { resultCode = 400 }
                    }
                }
            } catch (t: Throwable) {
                Log.d("retrofitMy", "remote data source impl -> ${t.printStackTrace()}")
                NetworkResponse().apply { resultCode = 500 }
            }
        }
    }
}