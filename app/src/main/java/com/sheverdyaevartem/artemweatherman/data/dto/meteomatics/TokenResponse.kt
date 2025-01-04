package com.sheverdyaevartem.artemweatherman.data.dto.meteomatics

import com.google.gson.annotations.SerializedName
import com.sheverdyaevartem.artemweatherman.data.dto.NetworkResponse

data class TokenResponse(@SerializedName("access_token") val accessToken: String) :
    NetworkResponse()