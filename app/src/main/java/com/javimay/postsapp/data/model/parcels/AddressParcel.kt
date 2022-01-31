package com.javimay.postsapp.data.model.parcels

import android.os.Parcel
import android.os.Parcelable

class AddressParcel(
    val _id: Int,
    val city: String,
    val geo: GeoParcel,
    val street: String,
    val suite: String,
    val zipcode: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readParcelable<GeoParcel>(ClassLoader.getSystemClassLoader())!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeParcelable(geo, flags)
        parcel.writeString(city)
        parcel.writeString(street)
        parcel.writeString(suite)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddressParcel> {
        override fun createFromParcel(parcel: Parcel): AddressParcel {
            return AddressParcel(parcel)
        }

        override fun newArray(size: Int): Array<AddressParcel?> {
            return arrayOfNulls(size)
        }
    }
}