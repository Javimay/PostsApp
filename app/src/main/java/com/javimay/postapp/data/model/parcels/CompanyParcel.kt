package com.javimay.postsapp.data.model.parcels

import android.os.Parcel
import android.os.Parcelable

class CompanyParcel(
    val _id: Int,
    val bs: String,
    val catchPhrase: String,
    val name: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeString(bs)
        parcel.writeString(catchPhrase)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompanyParcel> {
        override fun createFromParcel(parcel: Parcel): CompanyParcel {
            return CompanyParcel(parcel)
        }

        override fun newArray(size: Int): Array<CompanyParcel?> {
            return arrayOfNulls(size)
        }
    }
}