package com.sheverdyaevartem.artemweatherman.data.api

import com.sheverdyaevartem.artemweatherman.data.dto.NetworkResponse

interface RemoteDataSource {
    suspend fun doRequest(dto: Any): NetworkResponse
}