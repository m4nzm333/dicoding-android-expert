package com.unlistedi.tontonanterbaikku.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.db.ReminderConfigHelper

class AlarmDailyReceiver : BroadcastReceiver(){
    companion object {
        val NOTIFICATION_ID = 1
        var CHANNEL_ID = "channel_01"
        var CHANNEL_NAME: CharSequence = "Unlisted channel"
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        val reminderConfigHelper = ReminderConfigHelper(context)
        reminderConfigHelper.open()
        if (reminderConfigHelper.getDailyReminder() == 1) {
            val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
                .setContentTitle("Ayo buka kembali aplikasi ini.")
                .setContentText("Anda belum buka aplikasi ini seharian. Bukain plis.")
                .setSubText("")
                .setAutoCancel(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)
                mBuilder.setChannelId(CHANNEL_ID)
                mNotificationManager.createNotificationChannel(channel)
            }
            val notification = mBuilder.build()
            mNotificationManager.notify(NOTIFICATION_ID, notification)
        }
        reminderConfigHelper.close()
    }
}