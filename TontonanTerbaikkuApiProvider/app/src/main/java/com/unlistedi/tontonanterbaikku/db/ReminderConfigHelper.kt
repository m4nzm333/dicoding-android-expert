package com.unlistedi.tontonanterbaikku.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class ReminderConfigHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = DatabaseContract.ReminderConfigColumns.TABLE_NAME
        private lateinit var dataBaseHelper: DatabaseHelper
        private var INSTANCE: ReminderConfigHelper? = null
        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): ReminderConfigHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ReminderConfigHelper(context)
                    }
                }
            }
            return INSTANCE as ReminderConfigHelper
        }
    }

    init {
        dataBaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }
    fun close() {
        dataBaseHelper.close()
        if (database.isOpen)
            database.close()
    }

    fun setDialyReminder(status : Boolean){
        this.open()
        val contentValue = ContentValues()
        val statusString : String = if (status){
            "1"
        } else {
            "0"
        }
        contentValue.put("config_value", statusString)
        database.update(DATABASE_TABLE, contentValue, "${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} = \"daily_reminder\"", null)
        database.close()
    }
    fun setTodayRelease(status : Boolean){
        this.open()
        val contentValue = ContentValues()
        val statusString : String = if (status){
            "1"
        } else {
            "0"
        }
        contentValue.put("config_value", statusString)
        database.update(DATABASE_TABLE, contentValue, "${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} = \"today_release\"", null)
        database.close()
    }

    @SuppressLint("Recycle")
    fun getDailyReminder() : Int? {
        this.open()
        val selectQuery = "SELECT  * FROM $DATABASE_TABLE WHERE ${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} = ?"
        database.rawQuery(selectQuery, arrayOf("daily_reminder")).use { // .use requires API 16
            if (it.moveToFirst()) {
                return it.getInt(it.getColumnIndex("config_value"))
            }
        }
        return null
    }
    fun getTodayRelease() : Int? {
        this.open()
        val selectQuery = "SELECT  * FROM $DATABASE_TABLE WHERE ${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} = ?"
        database.rawQuery(selectQuery, arrayOf("today_release")).use { // .use requires API 16
            if (it.moveToFirst()) {
                return it.getInt(it.getColumnIndex("config_value"))
            }
        }
        return null
    }


}