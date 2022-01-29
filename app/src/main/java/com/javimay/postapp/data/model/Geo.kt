package com.javimay.postsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javimay.postsapp.utils.TABLE_GEO

@Entity(tableName = TABLE_GEO)
data class Geo(
    @PrimaryKey(autoGenerate = true)
    val idGeo: Int,
    val lat: String,
    val lng: String
)