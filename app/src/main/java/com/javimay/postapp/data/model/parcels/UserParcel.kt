package com.javimay.postsapp.data.model.parcels

import android.os.Parcel
import android.os.Parcelable

class UserParcel(
    val idUser: Int,
    val address: AddressParcel,
    val company: CompanyParcel,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable<AddressParcel>(ClassLoader.getSystemClassLoader())!!,
        parcel.readParcelable<CompanyParcel>(ClassLoader.getSystemClassLoader())!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idUser)
        parcel.writeParcelable(address,flags)
        parcel.writeParcelable(company,flags)
        parcel.writeString(email)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(username)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserParcel> {
        override fun createFromParcel(parcel: Parcel): UserParcel {
            return UserParcel(parcel)
        }

        override fun newArray(size: Int): Array<UserParcel?> {
            return arrayOfNulls(size)
        }
    }
}