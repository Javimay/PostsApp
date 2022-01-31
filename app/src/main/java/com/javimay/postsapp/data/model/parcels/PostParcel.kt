package com.javimay.postsapp.data.model.parcels

import android.os.Parcel
import android.os.Parcelable
import com.javimay.postsapp.data.model.Post

class PostParcel(
    val idPost: Int,
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val favorite: Boolean,
    val read: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPost)
        parcel.writeString(body)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(userId)
        parcel.writeByte(if (favorite) 1 else 0)
        parcel.writeByte(if (read) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostParcel> {
        override fun createFromParcel(parcel: Parcel): PostParcel {
            return PostParcel(parcel)
        }

        override fun newArray(size: Int): Array<PostParcel?> {
            return arrayOfNulls(size)
        }
    }

    private fun toPost(postParcel: PostParcel): Post {
        return Post(
            postParcel.idPost,
            postParcel.body,
            postParcel.id,
            postParcel.title,
            postParcel.userId,
            postParcel.favorite,
            postParcel.read,
        )
    }
}