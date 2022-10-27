package com.unlistedi.tontonanterbaikku.db

import android.net.Uri
import android.provider.BaseColumns

internal class DatabaseContract {

    internal class MoviesFavoriteColumns : BaseColumns {

        companion object {
            const val TABLE_NAME = "movies_favorite"
            const val _ID = "_id"

            const val AUTHORITY = "com.unlistedi.tontonanterbaikku"
            const val SCHEME = "content"
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
    internal class TvShowFavoriteColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "tv_show_favorite"
            const val _ID = "_id"
        }
    }

    internal class ReminderConfigColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "reminder_config"
            const val CONFIG_NAME = "config_name"
            const val CONFIG_VALUE = "config_value"
        }
    }
}