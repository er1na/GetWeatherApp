package com.example.getweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.getweatherapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GetWeather().getWeatherNews()

        binding.indicateButton.setOnClickListener{
            GetWeather().getWeatherNews()
            Thread.sleep(3000)
            binding.weatherDataText.text = GetWeather().result
        }

    }



}
