package com.example.newsapp.Location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class GetLocation(private val context: Context) {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private lateinit var geocoder: Geocoder

    fun getLocation(): String? {
        geocoder = Geocoder(context, Locale.getDefault())

        // Check for location permission
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permission
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return null
        } else {
            // Permission already granted
            return requestLocation()
        }
    }


    private fun requestLocation(): String {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = getLastKnownLocation(locationManager)
        if (location != null) {
            val countryCode = getCountryCode(location.latitude, location.longitude)
            Log.d("Location1",countryCode)
            return countryCode
           // textView.text = "Country Code: $countryCode"
        } else {
           // textView.text = "Location not available"
            return ""
        }
    }

    private fun getLastKnownLocation(locationManager: LocationManager): Location? {
        val providers = locationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return null
            }
            val l = locationManager.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                bestLocation = l
            }
        }
        return bestLocation
    }

    private fun getCountryCode(latitude: Double, longitude: Double): String {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            return addresses[0]?.countryCode ?: "N/A"
        }
        return "N/A"
    }
}
