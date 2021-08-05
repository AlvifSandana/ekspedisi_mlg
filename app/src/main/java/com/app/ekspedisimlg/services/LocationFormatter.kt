package com.app.ekspedisimlg.services

import android.content.Context
import android.location.Geocoder
import java.lang.Exception
import java.util.*

interface LocationFormatter {
    fun getLocation(context: Context, latitude: Double?, longitude: Double?): String = try {
        val latitudeLocation = latitude ?: 0.0
        val longitudeLocation = longitude ?: 0.0
        val geocoder = Geocoder(context, Locale.getDefault())
        val address = geocoder.getFromLocation(latitudeLocation, longitudeLocation, 1)
        address[0].getAddressLine(0)
    } catch (e: Exception) {
        ""
    }
}