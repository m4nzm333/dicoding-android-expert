package com.unlistedi.tontonanterbaikkuapi.db

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class MoviesFavoriteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "movies_favorite"
            const val _ID = "_id"
        }
    }
    internal class TvShowFavoriteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "tv_show_favorite"
            const val _ID = "_id"
        }
    }
}