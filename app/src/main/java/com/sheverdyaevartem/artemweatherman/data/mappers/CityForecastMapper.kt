package com.sheverdyaevartem.artemweatherman.data.mappers

import com.sheverdyaevartem.artemweatherman.data.dto.WeatherResponse
import com.sheverdyaevartem.artemweatherman.domain.model.CityForecast

class CityForecastMapper {

    fun map(weatherResponse: WeatherResponse): CityForecast {
        return with(weatherResponse) {
            CityForecast(with(current) {
                CityForecast.CurrentCity(
                    cloud,
                    feelslike_c,
                    CityForecast.CurrentCity.ConditionCIty(condition.icon, condition.text),
                    temp_c
                )
            })
        }
    }
}