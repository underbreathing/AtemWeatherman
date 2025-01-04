package com.sheverdyaevartem.artemweatherman.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import com.sheverdyaevartem.artemweatherman.presentation.screens.MainCard
import com.sheverdyaevartem.artemweatherman.presentation.ui.theme.ArtemWeathermanTheme
import com.sheverdyaevartem.artemweatherman.presentation.viewmodel.MainViewModel
import com.sheverdyaevartem.artemweatherman.presentation.viewmodel.TemperatureState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val temperatureState = mutableStateOf("...")
        lifecycleScope.launch {
            viewModel.observeTemperatureState.collect {
                when (it) {
                    TemperatureState.Error -> {}
                    TemperatureState.Loading -> {}
                    is TemperatureState.Success -> {
                        temperatureState.value = it.temperature
                        Log.d(
                            "retrofitMy",
                            "temperature in voronezh it is ${it.temperature}"
                        )
                    }
                }
            }
        }
        setContent {
            ArtemWeathermanTheme {
                MainCard(temperatureState)
            }
        }
    }


}

