package com.sbz.weatherapplication.ui

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sbz.weatherapplication.R

class SplashScreen : AppCompatActivity() {

    lateinit var mFusedLocation: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mFusedLocation = LocationServices
            .getFusedLocationProviderClient(this)

        getLastLocation()

    }

    private fun getLastLocation(){
        if(checkPermission()){
            if(locationEnable()){
                mFusedLocation.lastLocation.addOnCompleteListener {
                    task ->
                    var location: Location?=task.result
                }
            }
        }else{
            requestPermission()
        }
    }

    private fun locationEnable(): Boolean {

    }

    private fun requestPermission() {

    }

    private fun checkPermission(): Boolean {

    }
}