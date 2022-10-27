package com.unlistedi.tontonanterbaikku.widget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import com.unlistedi.tontonanterbaikku.db.MoviesHelper
import kotlin.collections.ArrayList

/**
 * Created by dicoding on 1/9/2017.
 */

internal class StackRemoteViewsFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {

    private val mWidgetItems = ArrayList<Bitmap>()
    var movieList : ArrayList<Tontonan> = ArrayList()
    val moviesHelper = MoviesHelper(mContext)
    private val API_KEY = "?api_key=0e591f36a123d98c31f062724d86e10e"
    private val URL = "https://api.themoviedb.org/3/movie/"
    @RequiresApi(Build.VERSION_CODES.N)
    private var language = mContext.resources.configuration.locales[0].toLanguageTag()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        when(mContext.resources.configuration.locales[0].toLanguageTag()){
            "en-US" -> language = "en-US"
            "id-ID" -> language = "id-ID"
        }
        moviesHelper.open()
        val cursor = moviesHelper.queryAll()
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast){
                movieList.add(Tontonan(
                    cursor.getString(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    "",
                    "",
                    "",
                    cursor.getString(cursor.getColumnIndex("poster"))
                ))
                cursor.moveToNext()
            }
        }
        moviesHelper.close()
    }

    override fun onDataSetChanged() {
        for(a in movieList ){
            var theBitmap = Glide.
                with(mContext)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/${a.poster}")
                .into(200, 100)
                .get()
            mWidgetItems.add(theBitmap)
        }
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName,
            R.layout.widget_item
        )
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems[position])

        val extras = bundleOf(
            ImagesBannerWidget.EXTRA_ITEM to position,
            ImagesBannerWidget.TITLE to movieList[position].nama
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

}