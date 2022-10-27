package com.unlistedi.tontonanterbaikku.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.unlistedi.tontonanterbaikku.db.DatabaseContract.MoviesFavoriteColumns.Companion.AUTHORITY
import com.unlistedi.tontonanterbaikku.db.DatabaseContract.MoviesFavoriteColumns.Companion.CONTENT_URI
import com.unlistedi.tontonanterbaikku.db.DatabaseContract.MoviesFavoriteColumns.Companion.TABLE_NAME
import com.unlistedi.tontonanterbaikku.db.MoviesHelper

class MoviesProvider : ContentProvider() {
    companion object {
        private const val NOTE = 1
        private const val NOTE_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var moviesHelper: MoviesHelper
        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTE)
            sUriMatcher.addURI(AUTHORITY,
                "$TABLE_NAME/#",
                NOTE_ID)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val deleted: Int = when (NOTE_ID) {
            sUriMatcher.match(uri) -> moviesHelper.deleteById(uri.lastPathSegment.toString())
            else -> 0
        }
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return deleted
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun onCreate(): Boolean {
        moviesHelper = MoviesHelper.getInstance(context as Context)
        moviesHelper.open()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when (sUriMatcher.match(uri)) {
            NOTE -> cursor = moviesHelper.queryAll()
            NOTE_ID -> cursor = moviesHelper.queryById(uri.lastPathSegment.toString())
            else -> cursor = null
        }
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 1
    }
}
