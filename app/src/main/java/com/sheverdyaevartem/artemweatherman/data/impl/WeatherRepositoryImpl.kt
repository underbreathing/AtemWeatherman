package com.sheverdyaevartem.artemweatherman.data.impl

import com.sheverdyaevartem.artemweatherman.data.api.RemoteDataSource
import com.sheverdyaevartem.artemweatherman.data.dto.TemperatureRequest
import com.sheverdyaevartem.artemweatherman.data.dto.TemperatureResponse
import com.sheverdyaevartem.artemweatherman.data.mappers.TemperatureMapper
import com.sheverdyaevartem.artemweatherman.domain.model.Temperature
import com.sheverdyaevartem.artemweatherman.domain.repository.WeatherRepository
import com.sheverdyaevartem.artemweatherman.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val temperatureMapper: TemperatureMapper
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
}