package com.sheverdyaevartem.artemweatherman.data.impl

import com.sheverdyaevartem.artemweatherman.data.api.MeteomaticsApi
import com.sheverdyaevartem.artemweatherman.data.api.RemoteDataSource
import com.sheverdyaevartem.artemweatherman.data.dto.NetworkResponse
import com.sheverdyaevartem.artemweatherman.data.dto.TemperatureRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(val weatherService: MeteomaticsApi) : RemoteDataSource {

    override suspend fun doRequest(dto: Any): NetworkResponse {
        return withContext(Dispatchers.IO) {
            try {
                when (dto) {
                    is TemperatureRequest -> {
                        weatherService.getWeatherData(dto.currentTime, dto.coordinates)
                            .apply { resultCode = 200 }
                    }

                    else -> {
                        NetworkResponse().apply { resultCode = 400 }
                    }
                }
            } catch (t: Throwable) {
                NetworkResponse().apply { resultCode = 500 }
            }
        }
    }
}