package com.sheverdyaevartem.artemweatherman.presentation.viewmodel

sealed interface TemperatureState {
    data class Success(val temperature: String) : TemperatureState
    data object Error : TemperatureState
    data object Loading: TemperatureState
}