package com.sheverdyaevartem.artemweatherman.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.artemweatherman.data.api.MeteomaticsApi
import com.sheverdyaevartem.artemweatherman.data.getCurrentTime
import com.sheverdyaevartem.artemweatherman.domain.interactors.ReceiptTemperatureUseCase
import com.sheverdyaevartem.artemweatherman.utils.Resource
import com.sheverdyaevartem.artemweatherman.utils.defaultCoordinates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(val weatherUseCase: ReceiptTemperatureUseCase) : ViewModel() {

    val observeTemperatureState: StateFlow<TemperatureState> get() = temperatureVoronezhState

    private val temperatureVoronezhState: MutableStateFlow<TemperatureState> =
        MutableStateFlow(TemperatureState.Loading)

    fun getTemperatureInVoronezh() {
        val currentTime = getCurrentTime(MeteomaticsApi.TIME_FORMAT)
        viewModelScope.launch {
            val result = weatherUseCase.invoke(currentTime, defaultCoordinates)
            when (result) {
                is Resource.BadRequestError -> TODO()
                is Resource.ConnectionError -> TODO()
                is Resource.ServerError -> TODO()
                is Resource.Success -> {
                    temperatureVoronezhState.emit(TemperatureState.Success("${result.data}\'C"))
                }

                is Resource.UnknownError -> TODO()
            }
        }
    }
}

const val USER_NAME = "artemforecastcom_forecaster_artemforecast"
const val PASSWORD = "l1XSyiF291"

class AuthInterceptor(private val username: String, private val password: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", Credentials.basic(username, password))
            .build()
        return chain.proceed(request)
    }
}

fun createRetrofitClient(username: String, password: String): Retrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(username, password))
        .build()

    return Retrofit.Builder()
        .baseUrl("https://login.meteomatics.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createRetrofitClient(): Retrofit {
    val client = OkHttpClient.Builder().build()

    return Retrofit.Builder()
        .baseUrl("https://login.meteomatics.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


data class TokenResponse(
    val access_token: String
)



