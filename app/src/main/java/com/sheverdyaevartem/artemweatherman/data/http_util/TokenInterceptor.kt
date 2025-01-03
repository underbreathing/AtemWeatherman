package com.sheverdyaevartem.artemweatherman.data.http_util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor(private val accessToken: String) : Interceptor {

    companion object {
        private const val PARAMETER_NAME = "access_token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        //получаем url
        val originalUrl = originalRequest.url()

        //создаем новуй Url с добавленным новым параметром токена
        val newHttpUrl = originalUrl.newBuilder()
            .addQueryParameter(PARAMETER_NAME, accessToken)
            .build()
        //создаем новый запрос с обновленным URL
        val newRequest: Request = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()
        return chain.proceed(newRequest)
    }
}