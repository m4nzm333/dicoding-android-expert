package com.unlistedi.aplikasifavorite.db

import android.content.ContentProviderClient
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.unlistedi.aplikasifavorite.Tontonan

class MoviesHelper (val context: Context){
    lateinit var uri : Uri
    lateinit var contentProviderClient : ContentProviderClient

    fun queryAll() : Cursor{
        uri = Uri.parse("content://com.unlistedi.tontonanterbaikku/movies_favorite")
        contentProviderClient = context.contentResolver.acquireContentProviderClient(uri)
        return contentProviderClient.query(uri, null, null, null, null)
    }

    fun deleteMovies(tontonan: Tontonan){
        uri = Uri.parse("content://com.unlistedi.tontonanterbaikku/movies_favorite/"+tontonan.id)
        contentProviderClient = context.contentResolver.acquireContentProviderClient(uri)
        contentProviderClient.delete(uri, null, null)
    }

    fun queryById(tontonan: Tontonan) : Cursor {
        uri = Uri.parse("content://com.unlistedi.tontonanterbaikku/movies_favorite/"+tontonan.id)
        contentProviderClient = context.contentResolver.acquireContentProviderClient(uri)
        return contentProviderClient.query(uri, null, null, null, null)
    }
}