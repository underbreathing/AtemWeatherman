package com.sheverdyaevartem.artemweatherman.data

import java.text.SimpleDateFormat
import java.util.Locale

fun main() {

    println(getCurrentTime("YYYY-MM-DD'T'HH-MM-SS.SSS'+03:00'"))
}

fun getCurrentTime(format: String): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(System.currentTimeMillis())
}