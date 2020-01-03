package com.chozhanaadu.foregroundservice

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import com.chozhanaadu.foregroundservice.AppController.Companion.CHANNEL_1_ID
import com.chozhanaadu.foregroundservice.AppController.Companion.CHENNEL_2_ID

class ForeGrService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val extraText = intent?.getStringExtra("extraText");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent: Intent = Intent(this, MainActivity::class.java)
            val pendingIntent:PendingIntent = PendingIntent.getActivity(this, 1,intent, 0)

            val notification: Notification = Notification.Builder(this, CHENNEL_2_ID)
                .setContentText("Service text $extraText")
                .setContentTitle("Service title")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setContentIntent(pendingIntent)
                .build()

            startForeground(1, notification)
        }

//        stopSelf()

        return START_STICKY;

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
