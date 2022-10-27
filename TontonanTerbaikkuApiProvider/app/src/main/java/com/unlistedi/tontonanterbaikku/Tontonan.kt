package com.unlistedi.tontonanterbaikku

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tontonan(
    var id: String,
    var nama: String,
    var rilis: String,
    var score: String,
    var deskripsi: String,
    var poster: String
) : Parcelable