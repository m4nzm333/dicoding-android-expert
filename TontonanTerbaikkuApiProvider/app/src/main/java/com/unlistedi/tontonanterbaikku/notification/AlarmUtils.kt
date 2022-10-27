package com.unlistedi.tontonanterbaikku.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmUtils(val context: Context){
    lateinit var pendingIntentDaily : PendingIntent
    lateinit var pendingIntentRelease : PendingIntent
    lateinit var alarmManager : AlarmManager

    fun initAlarm(){
        val intentDaily = Intent(context, AlarmDailyReceiver::class.java)
        val intentRelease = Intent(context, AlarmReleaseReceiver::class.java)
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        pendingIntentDaily = PendingIntent.getBroadcast(context, 1, intentDaily, 0)
        pendingIntentRelease = PendingIntent.getBroadcast(context, 2, intentRelease, 0)
    }

    fun startAlarmDaily(){
        val calendarDaily = Calendar.getInstance()
        calendarDaily.set(Calendar.HOUR_OF_DAY, 7)
        calendarDaily.set(Calendar.MINUTE, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendarDaily.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntentDaily)
    }

    @SuppressLint("ShortAlarm")
    fun startAlarmRelease(){
        val calendarRelease = Calendar.getInstance()
        calendarRelease.set(Calendar.HOUR_OF_DAY, 8)
        calendarRelease.set(Calendar.MINUTE, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendarRelease.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntentRelease)
    }

    fun cancelAlarmDaily(){
        alarmManager.cancel(pendingIntentDaily)
    }
    fun cancelAlarmRelease(){
        alarmManager.cancel(pendingIntentRelease)
    }
}