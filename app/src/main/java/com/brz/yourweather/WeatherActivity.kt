package com.brz.yourweather

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        // Инициализация DrawerLayout и NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Отключаем стандартный заголовок

        // Свой заголовок
        val title = findViewById<TextView>(R.id.toolbar_title)

        // Устанавливаем дату в заголовок
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("E dd MMMM", Locale("ru"))
        val formattedDate = dateFormat.format(currentDate).replaceFirstChar { it.uppercaseChar() }
        title.text = formattedDate

        // Кнопка гамбургера справа
        val btnHamburger = findViewById<ImageView>(R.id.btnHamburger)
        btnHamburger.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navigationView)) {
                drawerLayout.closeDrawer(navigationView)
            } else {
                drawerLayout.openDrawer(navigationView)
            }
        }

        // Получаем данные из Intent и устанавливаем значения в UI (пример)
        val city = intent.getStringExtra("city") ?: "Неизвестно"
        val temperature = intent.getStringExtra("temperature") ?: "--°C"
        val condition = intent.getStringExtra("condition") ?: "Нет данных"
        val humidity = intent.getStringExtra("humidity") ?: "--%"
        val wind = intent.getStringExtra("wind") ?: "-- м/с"

        val pressure = intent.getStringExtra("pressure") ?: "-- гПа"
        val feelsLike = intent.getStringExtra("feelsLike") ?: "--°C"
        val dewPoint = intent.getStringExtra("dewPoint") ?: "--°C"
        val uv = intent.getStringExtra("uv") ?: "-"

        findViewById<TextView>(R.id.cityText).text = city
        findViewById<TextView>(R.id.temperatureText).text = temperature
        findViewById<TextView>(R.id.conditionText).text = condition
        findViewById<TextView>(R.id.humidityText).text = humidity
        findViewById<TextView>(R.id.windText).text = wind

        findViewById<TextView>(R.id.pressureText).text = pressure
        findViewById<TextView>(R.id.feelsLikeText).text = feelsLike
        findViewById<TextView>(R.id.dewPointText).text = dewPoint
        findViewById<TextView>(R.id.uvText).text = uv

        val weatherIcon = findViewById<ImageView>(R.id.weatherIcon)
        weatherIcon.setImageResource(R.drawable.sun)
    }
}
