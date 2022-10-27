package com.unlistedi.tontonanterbaikku

import android.os.Parcel
import android.os.Parcelable

class Tontonan(var nama: String, var rilis: String, var score: String, var deskripsi: String,
               var poster: Int
) : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nama)
        dest?.writeString(rilis)
        dest?.writeString(score)
        dest?.writeString(deskripsi)
        dest?.writeInt(poster)
    }
    override fun describeContents(): Int =0
    constructor(parcel: Parcel) : this(
        nama = parcel.readString(),
        rilis = parcel.readString(),
        score = parcel.readString(),
        deskripsi = parcel.readString(),
        poster = parcel.readInt()
    )
    companion object CREATOR : Parcelable.Creator<Tontonan> {
        override fun createFromParcel(parcel: Parcel): Tontonan {
            return Tontonan(parcel)
        }
        override fun newArray(size: Int): Array<Tontonan?> {
            return arrayOfNulls(size)
        }
    }
}