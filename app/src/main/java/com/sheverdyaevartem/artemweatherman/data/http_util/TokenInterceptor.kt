package com.sheverdyaevartem.artemweatherman.data.http_util

import android.util.Log
import com.sheverdyaevartem.artemweatherman.data.api.meteomatics.AuthApi
import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.PASSWORD
import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.TokenResponse
import com.sheverdyaevartem.artemweatherman.data.dto.meteomatics.USER_NAME
import kotlinx.coroutines.runBlocking
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

var accessToken =
    "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJ2IjoxLCJ1c2VyIjoiYXJ0ZW1mb3JlY2FzdGNvbV9mb3JlY2FzdGVyX2FydGVtZm9yZWNhc3QiLCJpc3MiOiJsb2dpbi5tZXRlb21hdGljcy5jb20iLCJleHAiOjE3MzU5MTcxODIsInN1YiI6ImFjY2VzcyJ9.9cDE7qdxW2VkskMbxvEtUi8ieoySNAadApfAxBgwcsfYOn-YW355z4ujXNO8wJtd5ayHbSVOuY-IMcjkuH_OcQ"

class TokenInterceptor(
    private val authApi: AuthApi
) : Interceptor {

    companion object {
        private const val PARAMETER_NAME = "access_token"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val incomingRequest = chain.request()

        //создаем новый Url с добавленным новым параметром токена
        val httpUrl = incomingRequest.url.newBuilder()
            .addQueryParameter(PARAMETER_NAME, accessToken)
            .build()
        //создаем новый запрос с обновленным URL
        val request: Request = incomingRequest.newBuilder()
            .url(httpUrl)
            .build()
        val response: Response = chain.proceed(request)

        if (response.code == 403) {
            response.close()

            val newTokenResp: TokenResponse? = runBlocking {
                try {
                    authApi.getToken(Credentials.basic(USER_NAME, PASSWORD))
                } catch (t: Throwable) {
                    Log.d(
                        "retrofitMy",
                        "catch in interceptor by regresh token ${t.printStackTrace()}"
                    )
                    null
                }
            }

            if (newTokenResp != null) {
                val newAccessToken = newTokenResp.accessToken
                accessToken = newAccessToken
                val resultUrl = incomingRequest.url.newBuilder()
                    .addQueryParameter(PARAMETER_NAME, newAccessToken)
                    .build()
                val resultRequest = incomingRequest.newBuilder()
                    .url(resultUrl)
                    .build()
                return chain.proceed(resultRequest)
            }
        }
        return response
    }
}