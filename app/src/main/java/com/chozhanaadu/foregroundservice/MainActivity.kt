package com.chozhanaadu.foregroundservice

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chozhanaadu.foregroundservice.AppController.Companion.CHANNEL_1_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var count = 1;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Start foreground service using notification channel id
        button.setOnClickListener {
            val intent = Intent(this, ForeGrService::class.java)
            intent.putExtra("extraText", editText.text.toString())
//            startService(intent) //or
            ContextCompat.startForegroundService(this, intent)
        }

        button2.setOnClickListener {
            createNotification()
        }


        btn_stop.setOnClickListener {
            val intent: Intent = Intent(this, ForeGrService::class.java)
            stopService(intent)
        }
    }

    //Create location notification using chennal id
    fun createNotification() {
        count++
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 1, intent, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification: Notification = Notification.Builder(this, CHANNEL_1_ID)
                .setContentTitle("Notification Title $count")
                .setContentText("Notification text")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_foreground
                    )
                )
                .setContentIntent(pendingIntent)
                .build()

            val manager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
            manager.notify(1, notification)
        }
    }
}
