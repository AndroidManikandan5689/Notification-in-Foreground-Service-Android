package com.chozhanaadu.foregroundservice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.os.Build
import androidx.core.app.NotificationManagerCompat

class AppController : Application() {

    companion object{
    var CHANNEL_1_ID = "chennal1"
        val CHENNEL_2_ID = "ServiceChannel"
    }
    override fun onCreate() {
        super.onCreate()

        createNotificationChennal()

    }

    fun createNotificationChennal()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_1_ID,
                "CHANNEL 1",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.description = "Channel 1 description"
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.lightColor = Color.GREEN

            val notificationChennal2 = NotificationChannel(CHENNEL_2_ID, "CHENNAL 2", NotificationManager.IMPORTANCE_HIGH)
            notificationChennal2.description = "Notification from service"

            val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            manager.createNotificationChannel(notificationChannel)
//            manager.createNotificationChannel(notificationChennal2)

            var list: List<NotificationChannel> = arrayListOf(notificationChannel, notificationChennal2)
            manager.createNotificationChannels(list)
        }

    }

}