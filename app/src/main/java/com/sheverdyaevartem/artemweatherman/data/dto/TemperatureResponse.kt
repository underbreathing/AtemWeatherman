package com.sheverdyaevartem.artemweatherman.data.dto

data class TemperatureResponse(
    val `data`: List<Data>,
    val dateGenerated: String,
    val status: String,
    val user: String,
    val version: String
) : NetworkResponse() {
    data class Data(
        val coordinates: List<Coordinate>,
        val parameter: String
    ) {
        data class Coordinate(
            val dates: List<Date>,
            val lat: Double,
            val lon: Double
        ) {
            data class Date(
                val date: String,
                val value: Double
            )
        }
    }
}