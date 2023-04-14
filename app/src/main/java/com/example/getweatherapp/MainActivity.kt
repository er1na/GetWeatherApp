package com.example.getweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.getweatherapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var result = ""
    private var lat = 35.689499
    private var lon = 139.691711

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.indicateButton.setOnClickListener{
            getWeatherNews()
            Thread.sleep(3000)
            binding.weatherDataText.text = result
        }

    }

    private fun unixTimeChange(unixTime: String): String{
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
        var now = Date(unixTime.toInt() * 1000L)
        return sdf.format(now)
    }

    private fun getWeatherNews(): Job = GlobalScope.launch{
        result = ""
        var API_KEY = "******"
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
            result += "${unixTimeChange(time)} $descriptionText \n\n"
        }
    }

}
