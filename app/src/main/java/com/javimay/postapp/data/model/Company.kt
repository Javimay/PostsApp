package com.javimay.postsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javimay.postsapp.utils.TABLE_COMPANY

@Entity(tableName = TABLE_COMPANY)
data class Company(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    val bs: String,
    val catchPhrase: String,
    val name: String
)