package com.sbz.weatherapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
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

    private fun setValues(response: JSONObject){
        val tvCity = findViewById<TextView>(R.id.tv_city)
        val tvLongitude = findViewById<TextView>(R.id.tv_long)
        val tvLatitude = findViewById<TextView>(R.id.tv_lat)
        tvCity.text = response.getString("name")
        tvLatitude.text = response.getJSONObject("coord").getString("lat")
        tvLongitude.text = response.getJSONObject("coord").getString("lon")


    }
}