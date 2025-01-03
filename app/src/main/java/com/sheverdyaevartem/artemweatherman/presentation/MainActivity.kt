package com.sheverdyaevartem.artemweatherman.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.sheverdyaevartem.artemweatherman.presentation.ui.theme.ArtemWeathermanTheme
import com.sheverdyaevartem.artemweatherman.presentation.viewmodel.MainViewModel
import com.sheverdyaevartem.artemweatherman.presentation.viewmodel.TemperatureState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.getTemperatureInVoronezh()
        setContent {
            ArtemWeathermanTheme {
                ShowPreview()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun ShowPreview() {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text("Temperature in Voronezh:")
        }
    }
}

