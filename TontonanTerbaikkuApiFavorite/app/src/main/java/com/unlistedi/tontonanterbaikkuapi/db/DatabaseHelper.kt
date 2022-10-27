package com.unlistedi.tontonanterbaikkuapi.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbnoteapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_MOVIES = "CREATE TABLE ${DatabaseContract.MoviesFavoriteColumns.TABLE_NAME} " +
                "(${DatabaseContract.MoviesFavoriteColumns._ID} INTEGER)"
        private const val SQL_CREATE_TABLE_TV_SHOW = "CREATE TABLE ${DatabaseContract.TvShowFavoriteColumns.TABLE_NAME} " +
                "(${DatabaseContract.TvShowFavoriteColumns._ID} INTEGER)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_MOVIES)
        db.execSQL(SQL_CREATE_TABLE_TV_SHOW)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.MoviesFavoriteColumns.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.TvShowFavoriteColumns.TABLE_NAME}")
        onCreate(db)
    }


}