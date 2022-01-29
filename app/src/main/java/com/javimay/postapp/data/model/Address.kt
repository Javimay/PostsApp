package com.javimay.postsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javimay.postsapp.utils.TABLE_ADDRESS

@Entity(tableName = TABLE_ADDRESS)
data class Address(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    val city: String,
//    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)