package com.sheverdyaevartem.artemweatherman.data.mappers

import com.sheverdyaevartem.artemweatherman.data.dto.TemperatureResponse
import com.sheverdyaevartem.artemweatherman.domain.model.Temperature

class TemperatureMapper {

    fun map(temperatureResponse: TemperatureResponse): Temperature {
        return with(temperatureResponse) {
            Temperature(data.first().coordinates.first().dates.first().value)
        }
    }

}