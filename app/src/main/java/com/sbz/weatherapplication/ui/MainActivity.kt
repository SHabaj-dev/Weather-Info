package com.sbz.weatherapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sbz.weatherapplication.R
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var longitude: String
    private lateinit var latitude: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        longitude = intent.getStringExtra("long").toString()
        latitude = intent.getStringExtra("late").toString()

        getJsonData(latitude, longitude)

    }

    private fun getJsonData(lat: String?, lon: String?) {

        val queue = Volley.newRequestQueue(this)
        val API_KEY = "47989fac6745f04503073f5f0a467617"
        val url =
            "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
//                Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
                setValues(response)
            },
            {
                Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show()
            })

        queue.add(jsonRequest)
    }

    private fun setValues(response: JSONObject) {
        val tvCity = findViewById<TextView>(R.id.tv_city)
        val tvCountry = findViewById<TextView>(R.id.tv_country)
//        val tvMain = findViewById<TextView>(R.id.tv_main)
        val tvTemperature = findViewById<TextView>(R.id.tv_current_temp)
        val tvFeel = findViewById<TextView>(R.id.tv_feel)
        var temperature = response.getJSONObject("main").getString("temp")
        temperature = (((temperature).toFloat() - 273.15).toInt()).toString()

        val country = response.getJSONObject("sys").getString("country")


        tvFeel.text = response.getJSONArray("weather").getJSONObject(0).getString("main").toString()
        val getIconId = response.getJSONArray("weather").getJSONObject(0).getInt("id")

//        Log.d("COUNTERY", country.toString())
        tvCity.text = response.getString("name")
        setAnimation(getIconId)
        tvTemperature.text = temperature
        tvCountry.text = country
        tvCountry.visibility = View.VISIBLE


    }

    private fun setAnimation(IconId: Int) {
        val animationView = findViewById<LottieAnimationView>(R.id.la_animation_weather)
        var animation = R.raw.animation_loading
        when (IconId) {
            in 200..299 -> {
                animation = R.raw.thunderstorm
            }
            in 300..399 -> {
                animation = R.raw.drizzle
            }
            in 500..599 -> {
                animation = R.raw.rain
            }
            in 600..699 -> {
                animation = R.raw.snow
            }
            in 700..799 -> {
                animation = R.raw.mist
            }
            800 -> {
                animation = R.raw.clear
            }
            in 801..899 -> {
                animation = R.raw.clouds
            }
        }

        animationView.setAnimation(animation)
        animationView.playAnimation()
    }
}