package com.sheverdyaevartem.artemweatherman.data.api.meteomatics

import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.TokenResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi{
    @GET("api/v1/token")
    suspend fun getToken(
        @Header("Authorization") authorization: String
    ): TokenResponse
}