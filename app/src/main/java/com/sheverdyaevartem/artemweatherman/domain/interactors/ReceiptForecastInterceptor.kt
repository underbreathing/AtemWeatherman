package com.sheverdyaevartem.artemweatherman.domain.interactors

import com.sheverdyaevartem.artemweatherman.domain.model.Temperature
import com.sheverdyaevartem.artemweatherman.domain.model.CityForecast
import com.sheverdyaevartem.artemweatherman.domain.repository.WeatherRepository
import com.sheverdyaevartem.artemweatherman.utils.Resource

class ReceiptForecastInterceptor(private val weatherRepository: WeatherRepository) {

    suspend fun getTemperatureByDateAndCoordinates(
        datetime: String,
        coordinates: String
    ): Resource<Temperature> {
        return weatherRepository.getTemperature(datetime, coordinates)
    }

    suspend fun getForecastInCity(city: String): Resource<CityForecast> {
        return weatherRepository.getWeatherInCity(city)
    }
}