package com.brz.yourweather

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        // Получаем данные из Intent
        val city = intent.getStringExtra("city") ?: "Неизвестно"
        val temperature = intent.getStringExtra("temperature") ?: "--°C"
        val condition = intent.getStringExtra("condition") ?: "Нет данных"
        val humidity = intent.getStringExtra("humidity") ?: "--%"
        val wind = intent.getStringExtra("wind") ?: "-- м/с"

        // Устанавливаем значения в элементы
        findViewById<TextView>(R.id.cityText).text = city
        findViewById<TextView>(R.id.temperatureText).text = temperature
        findViewById<TextView>(R.id.conditionText).text = condition
        findViewById<TextView>(R.id.humidityText).text = "Влажность: $humidity"
        findViewById<TextView>(R.id.windText).text = "Ветер: $wind"

        // Пример установки иконки — по погоде можно менять
        val weatherIcon = findViewById<ImageView>(R.id.weatherIcon)
        weatherIcon.setImageResource(R.drawable.sun) // пока что всегда солнце
    }
}
