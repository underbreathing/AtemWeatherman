package com.sheverdyaevartem.artemweatherman.domain.repository

import com.sheverdyaevartem.artemweatherman.domain.model.Temperature
import com.sheverdyaevartem.artemweatherman.domain.model.CityForecast
import com.sheverdyaevartem.artemweatherman.utils.Resource

interface WeatherRepository {

    suspend fun getTemperature(datetime: String, coordinates: String): Resource<Temperature>

    suspend fun getWeatherInCity(city: String): Resource<CityForecast>
}