package com.sheverdyaevartem.artemweatherman.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sheverdyaevartem.artemweatherman.R
import com.sheverdyaevartem.artemweatherman.presentation.ui.theme.YellowLight

@Composable
fun MainCard(temperatureState: MutableState<String>) {
    Image(
        painter = painterResource(R.drawable.background_image),
        contentDescription = "background image",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.72f),
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = YellowLight),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "20 Jun 2022 13:00",
                            style = TextStyle(fontSize = 15.sp),
                            color = Color.White,
                            modifier = Modifier.padding(
                                top = 14.dp, start = 12.dp
                            )
                        )
                        AsyncImage(
                            model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                            contentDescription = "image",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(
                                    top = 9.dp, end = 12.dp
                                )
                        )
                    }
                    Text(
                        text = "Saint-Petersburg",
                        style = TextStyle(fontSize = 24.sp),
                        color = Color.White,
                    )
                    Text(
                        text = "-3°С",
                        style = TextStyle(fontSize = 64.sp),
                        color = Color.White,
                    )
                    Text(
                        text = "Sunny",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                tint = Color.White,
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = "im3",
                            )
                        }
                        Text(
                            "-1°С/-6°С",
                            style = TextStyle(fontSize = 16.sp),
                            color = Color.White
                        )
                        IconButton(onClick = {}) {
                            Icon(
                                tint = Color.White,
                                painter = painterResource(R.drawable.ic_sync),
                                contentDescription = "im4"
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainCard(remember { mutableStateOf("25 'C") })
}