package com.unlistedi.tontonanterbaikku.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import com.unlistedi.tontonanterbaikku.db.ReminderConfigHelper
import org.json.JSONObject
import java.lang.Exception
import java.util.*


class AlarmReleaseReceiver : BroadcastReceiver(){
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        val reminderConfigHelper = ReminderConfigHelper(context)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        reminderConfigHelper.open()
        if (reminderConfigHelper.getTodayRelease() == 1){
            val API_KEY = "0e591f36a123d98c31f062724d86e10e"
            val URL = "https://api.themoviedb.org/3/discover/movie?api_key="
            val requestQueue = Volley.newRequestQueue(context)
            val URL_REQUEST = "$URL$API_KEY&primary_release_date.gte=${year}-${month}-${day}&primary_release_date.lte=${year}-${month}-${day}"
            val stringRequest = StringRequest(
                Request.Method.GET,
                URL_REQUEST,
                Response.Listener<String> { response ->
                    val jsonResponse = JSONObject(response)
                    val resultJson = jsonResponse.getJSONArray("results")
                    lateinit var movie : Tontonan
                    for (i in 0 until resultJson.length()){
                        val itemJsonObject = resultJson.getJSONObject(i)
                        movie = Tontonan(
                            itemJsonObject.getString("id"),
                            itemJsonObject.getString("original_title"),
                            itemJsonObject.getString("release_date"),
                            itemJsonObject.getString("vote_average"),
                            itemJsonObject.getString("overview"),
                            itemJsonObject.getString("poster_path")
                        )
                        if (context != null) {
                            showNotification(context, movie)
                        }
                    }
                },
                Response.ErrorListener { error ->
                    Log.e("AlarmReleaseReceiver", error.toString())
                }
            )
            requestQueue.add(stringRequest)
            try {
                Thread.sleep(10000)
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
        reminderConfigHelper.close()
    }

    private fun showNotification(context: Context, movie : Tontonan){
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val CHANNEL_ID = "channel_${movie.id}"
        val CHANNEL_NAME: CharSequence = "Unlisted channel"
        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
            .setContentTitle(movie.nama)
            .setContentText("${movie.nama} rilis ini hari")
            .setSubText("")
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            mBuilder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }
        val notification = mBuilder.build()
        val random = Math.random()
        mNotificationManager.notify(movie.id.toInt(), notification)
    }
}