package com.javimay.postsapp.data.model.parcels

import android.os.Parcel
import android.os.Parcelable

class GeoParcel(
    val _id: Int,
    val lat: String,
    val lng: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeString(lat)
        parcel.writeString(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GeoParcel> {
        override fun createFromParcel(parcel: Parcel): GeoParcel {
            return GeoParcel(parcel)
        }

        override fun newArray(size: Int): Array<GeoParcel?> {
            return arrayOfNulls(size)
        }
    }
}