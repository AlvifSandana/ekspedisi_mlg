package com.app.ekspedisimlg.services

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.activities.MainActivity

class LocationService : Service(), LocationFormatter {

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var address: String = ""
    private val channelID = "LocationService"
    private var location: Location? = null
    private var locationManager: LocationManager? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.putExtra("SERVICE_STATE", true)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Update Supir Location Service")
            .setContentText("Running")
            .setSmallIcon(R.drawable.ic_outline_info_24)
            .setContentIntent(pendingIntent)
            .build()
        getLocation()
        startForeground(1, notification)
        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelID,
                "Location Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == 0
        ) {
            location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            latitude = location?.latitude ?: 0.0
            longitude = location?.longitude ?: 0.0
            address = getLocation(this, latitude, longitude)
        }
        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            2000,
            10f,
            locationListener
        )

    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitude = location.latitude
            longitude = location.longitude
        }

    }

    companion object {
        fun startService(context: Context) {
            val startIntent = Intent(context, LocationService::class.java)
            startIntent.putExtra("SERVICE_STATE", "started")
            ContextCompat.startForegroundService(context, startIntent)
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, LocationService::class.java)
            context.stopService(stopIntent)
        }
    }
}

