package com.example.getweatherapp

import java.text.SimpleDateFormat
import java.util.*

class UnixTimeChange {

    fun unixTimeChange(unixTime: String): String{
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        var now = Date(unixTime.toInt() * 1000L)
        return sdf.format(now)
    }

}