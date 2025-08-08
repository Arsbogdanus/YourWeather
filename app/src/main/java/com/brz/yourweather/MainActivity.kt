package com.brz.yourweather

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Устанавливаем "пустой" layout или splash — зависит от твоего дизайна
        setContentView(R.layout.activity_weather)

        // Пример данных
        val city = "Киев"
        val temperature = "24°C"
        val condition = "Ясно"
        val humidity = "75%"
        val wind = "5 м/с"
        val pressure = "1012 гПа"
        val feelsLike = "23°C"
        val dewPoint = "18°C"
        val uv = "3"

        // Передача данных через Intent

        val intent = Intent(this, WeatherActivity::class.java).apply {
            putExtra("city", city)
            putExtra("temperature", temperature)
            putExtra("condition", condition)
            putExtra("humidity", humidity)
            putExtra("wind", wind)

            putExtra("pressure", pressure)
            putExtra("feelsLike", feelsLike)
            putExtra("dewPoint", dewPoint)
            putExtra("uv", uv)
        }

        startActivity(intent)

        // Закрываем MainActivity, если не нужна
        finish()
    }
}
