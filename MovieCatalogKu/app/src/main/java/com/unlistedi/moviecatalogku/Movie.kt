package com.unlistedi.moviecatalogku

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Parcelize
class Movie(
    val nama:String,
    val rilis:String,
    val score:String,
    val deskripsi:String,
    val poster:Int
    ) : Parcelable {

    constructor(parcel: Parcel) : this(
        nama = parcel.readString(),
        rilis = parcel.readString(),
        score = parcel.readString(),
        deskripsi = parcel.readString(),
        poster = parcel.readInt()
    )

    private companion object : Parceler<Movie> {
        override fun Movie.write(parcel: Parcel, flags: Int) {
            parcel?.writeString(nama)
            parcel?.writeString(rilis)
            parcel?.writeString(score)
            parcel?.writeString(deskripsi)
            parcel?.writeInt(poster)
        }
        override fun create(parcel: Parcel): Movie {
            return Movie(parcel)
        }
    }

}