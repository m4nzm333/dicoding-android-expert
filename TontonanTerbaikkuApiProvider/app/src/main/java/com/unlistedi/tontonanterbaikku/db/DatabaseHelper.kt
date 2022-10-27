package com.unlistedi.tontonanterbaikku.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbnoteapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_MOVIES = "CREATE TABLE ${DatabaseContract.MoviesFavoriteColumns.TABLE_NAME} " +
                "(${DatabaseContract.MoviesFavoriteColumns._ID} INTEGER, title TEXT, poster TEXT)"
        private const val SQL_CREATE_TABLE_TV_SHOW = "CREATE TABLE ${DatabaseContract.TvShowFavoriteColumns.TABLE_NAME} " +
                "(${DatabaseContract.TvShowFavoriteColumns._ID} INTEGER)"
        private const val SQL_CREATE_TABLE_REMINDER_CONFIG = "CREATE TABLE ${DatabaseContract.ReminderConfigColumns.TABLE_NAME} " +
                "(${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} TEXT, " +
                "${DatabaseContract.ReminderConfigColumns.CONFIG_VALUE} INTEGER DEFAULT 0)"
        private const val SQL_INSERT_CONFIG_DAILY = "INSERT INTO ${DatabaseContract.ReminderConfigColumns.TABLE_NAME} " +
                "(${DatabaseContract.ReminderConfigColumns.CONFIG_NAME}) \n" +
                "SELECT \"daily_reminder\" \n" +
                "WHERE NOT EXISTS" +
                "(SELECT ${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} " +
                "FROM ${DatabaseContract.ReminderConfigColumns.TABLE_NAME} " +
                "WHERE ${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} = \"daily_reminder\")"
        private const val SQL_INSERT_CONFIG_RELEASE = "INSERT INTO ${DatabaseContract.ReminderConfigColumns.TABLE_NAME} " +
                "(${DatabaseContract.ReminderConfigColumns.CONFIG_NAME}) \n" +
                "SELECT \"today_release\" \n" +
                "WHERE NOT EXISTS" +
                "(SELECT ${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} " +
                "FROM ${DatabaseContract.ReminderConfigColumns.TABLE_NAME} " +
                "WHERE ${DatabaseContract.ReminderConfigColumns.CONFIG_NAME} = \"today_release\")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_MOVIES)
        db.execSQL(SQL_CREATE_TABLE_TV_SHOW)
        db.execSQL(SQL_CREATE_TABLE_REMINDER_CONFIG)
        db.execSQL(SQL_INSERT_CONFIG_DAILY)
        db.execSQL(SQL_INSERT_CONFIG_RELEASE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.MoviesFavoriteColumns.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.TvShowFavoriteColumns.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.ReminderConfigColumns.TABLE_NAME}")
        onCreate(db)
    }


}