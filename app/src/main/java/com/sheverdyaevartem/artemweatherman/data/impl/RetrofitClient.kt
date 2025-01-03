package com.sheverdyaevartem.artemweatherman.data.impl

import com.sheverdyaevartem.artemweatherman.presentation.viewmodel.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {


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
}