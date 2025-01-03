package com.sheverdyaevartem.artemweatherman.domain.interactors

import com.sheverdyaevartem.artemweatherman.domain.model.Temperature
import com.sheverdyaevartem.artemweatherman.domain.repository.WeatherRepository
import com.sheverdyaevartem.artemweatherman.utils.Resource

class ReceiptTemperatureUseCase(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(datetime: String, coordinates: String): Resource<Temperature> {
        return weatherRepository.getTemperature(datetime, coordinates)
    }
}