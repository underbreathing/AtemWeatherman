package com.sheverdyaevartem.artemweatherman.domain.model

import com.sheverdyaevartem.artemweatherman.data.dto.NetworkResponse

data class CityForecast(
    val currentCity: CurrentCity,
) : NetworkResponse() {
    data class CurrentCity(
        val cloud: Int,
        val feelsLikeC: Double,
        val conditionCIty: ConditionCIty,
        val tempC: Double,
    ) {
        data class ConditionCIty(
            val icon: String,
            val text: String
        )
    }


}