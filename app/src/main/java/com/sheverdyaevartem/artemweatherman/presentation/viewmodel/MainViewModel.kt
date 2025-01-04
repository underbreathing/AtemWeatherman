package com.sheverdyaevartem.artemweatherman.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.artemweatherman.data.api.meteomatics.MeteomaticsApi
import com.sheverdyaevartem.artemweatherman.domain.interactors.ReceiptForecastInterceptor
import com.sheverdyaevartem.artemweatherman.utils.Resource
import com.sheverdyaevartem.artemweatherman.utils.defaultCoordinates
import com.sheverdyaevartem.artemweatherman.utils.getCurrentTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val forecastInteractor: ReceiptForecastInterceptor) : ViewModel() {
    init {
        getTemperatureInVoronezh()
    }

    val observeTemperatureState: StateFlow<TemperatureState> get() = temperatureVoronezhState

    private val temperatureVoronezhState: MutableStateFlow<TemperatureState> =
        MutableStateFlow(TemperatureState.Loading)

    fun getTemperatureInVoronezh() {
        val currentTime = getCurrentTime(MeteomaticsApi.TIME_FORMAT)
        viewModelScope.launch {
            val result = forecastInteractor.getTemperatureByDateAndCoordinates(currentTime, defaultCoordinates)
            when (result) {
                is Resource.BadRequestError -> {}
                is Resource.ConnectionError -> {}
                is Resource.ServerError -> {}
                is Resource.Success -> {
                    temperatureVoronezhState.emit(TemperatureState.Success("${result.data.temperature}\'C"))
                }

                is Resource.UnknownError -> {}
            }
        }
    }
}



