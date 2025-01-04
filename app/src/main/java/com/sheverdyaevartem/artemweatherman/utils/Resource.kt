package com.sheverdyaevartem.artemweatherman.utils

sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    class ServerError<T> : Resource<T>
    class BadRequestError<T> : Resource<T>
    class ConnectionError<T> : Resource<T>
    class UnknownError<T> : Resource<T>
}