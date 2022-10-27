package com.unlistedi.tontonanterbaikku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unlistedi.tontonanterbaikku.db.ReminderConfigHelper
import com.unlistedi.tontonanterbaikku.notification.AlarmUtils
import kotlinx.android.synthetic.main.activity_alarm.*
import org.jetbrains.anko.toast

class AlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val reminderConfigHelper = ReminderConfigHelper(applicationContext)

        val dailyReminderStatus = reminderConfigHelper.getDailyReminder()
        val releaseTodayStatus = reminderConfigHelper.getTodayRelease()
        if (dailyReminderStatus != null) {
            swDailyReminder.isChecked = dailyReminderStatus == 1
        }
        if (releaseTodayStatus != null) {
            swReleaseToday.isChecked = releaseTodayStatus == 1
        }
        val alarmUtils = AlarmUtils(applicationContext)
        alarmUtils.initAlarm()
        swReleaseToday.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                reminderConfigHelper.setTodayRelease(true)
                toast("Today Reminder is on")
                alarmUtils.startAlarmRelease()
            } else {
                reminderConfigHelper.setTodayRelease(false)
                toast("Today Reminder is off")
                alarmUtils.cancelAlarmRelease()
            }
        }

        swDailyReminder.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                reminderConfigHelper.setDialyReminder(true)
                toast("Release Today is on")
                alarmUtils.startAlarmDaily()
            } else {
                reminderConfigHelper.setDialyReminder(false)
                toast("Release Today is of")
                alarmUtils.cancelAlarmDaily()
            }
        }
    }
}
