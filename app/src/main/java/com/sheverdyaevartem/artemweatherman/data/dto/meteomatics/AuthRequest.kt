package com.sheverdyaevartem.artemweatherman.data.dto.meteomatics

const val USER_NAME = "artemforecastcom_forecaster_artemforecast"
const val PASSWORD = "l1XSyiF291"

data class AuthRequest(val userName: String = USER_NAME, val password: String = PASSWORD)