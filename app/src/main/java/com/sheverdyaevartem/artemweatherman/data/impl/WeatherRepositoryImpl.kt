package com.sheverdyaevartem.artemweatherman.data.impl

import com.sheverdyaevartem.artemweatherman.data.api.RemoteDataSource
import com.sheverdyaevartem.artemweatherman.data.dto.FreeWeatherRequest
import com.sheverdyaevartem.artemweatherman.data.dto.WeatherResponse
import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.TemperatureRequest
import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.TemperatureResponse
import com.sheverdyaevartem.artemweatherman.data.mappers.TemperatureMapper
import com.sheverdyaevartem.artemweatherman.data.mappers.CityForecastMapper
import com.sheverdyaevartem.artemweatherman.domain.model.Temperature
import com.sheverdyaevartem.artemweatherman.domain.model.CityForecast
import com.sheverdyaevartem.artemweatherman.domain.repository.WeatherRepository
import com.sheverdyaevartem.artemweatherman.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val temperatureMapper: TemperatureMapper,
    private val cityForecastMapper: CityForecastMapper
) : WeatherRepository {

    override suspend fun getTemperature(
        datetime: String,
        coordinates: String
    ): Resource<Temperature> {
        return withContext(Dispatchers.IO) {
            val response = remoteDataSource.doRequest(TemperatureRequest(datetime, coordinates))
            if (response is TemperatureResponse) {
                Resource.Success(temperatureMapper.map(response))
            } else {
                when (response.resultCode) {
                    400 -> Resource.BadRequestError()
                    500 -> Resource.ServerError()
                    else -> Resource.UnknownError()
                }
            }
        }
    }

    override suspend fun getWeatherInCity(city: String): Resource<CityForecast> {
        return withContext(Dispatchers.IO) {
            val response = remoteDataSource.doRequest(FreeWeatherRequest(city))
            if (response is WeatherResponse) {
                Resource.Success(cityForecastMapper.map(response))
            } else {
                when (response.resultCode) {
                    400 -> Resource.BadRequestError()
                    500 -> Resource.ServerError()
                    else -> Resource.UnknownError()
                }
            }
        }
    }
}