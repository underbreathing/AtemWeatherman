package com.sheverdyaevartem.artemweatherman.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun getCurrentTime(format: String): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(System.currentTimeMillis())
}