package com.example.getweatherapp

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class GetWeather {

    var result = ""
    private var lat = 35.689499
    private var lon = 139.691711

    fun getWeatherNews(): Job = GlobalScope.launch{
        result = ""
        var API_KEY = "3641c8ce4e91f48596f6faa5c95ac6a4"
        var API_URL = "https://api.openweathermap.org/data/3.0/onecall?" +
                "lat=" + lat + "&" +
                "lon=" + lon + "&" +
                "lang=" + "ja" + "&" +
                "appid=" + API_KEY
        var url = URL(API_URL)
        var br = BufferedReader(InputStreamReader(url.openStream()))
        var str = br.readText()
        var json = JSONObject(str)
        var hourly = json.getJSONArray("hourly")

        for(i in 0..9){
            var firstObject = hourly.getJSONObject(i)
            var weatherList = firstObject.getJSONArray("weather").getJSONObject(0)
            var time = firstObject.getString("dt")
            var descriptionText = weatherList.getString("description")
            result += "${UnixTimeChange().unixTimeChange(time)} $descriptionText \n\n"
        }
    }

}