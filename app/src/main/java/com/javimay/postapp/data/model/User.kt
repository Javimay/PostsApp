package com.javimay.postsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javimay.postsapp.utils.TABLE_USERS

@Entity(tableName = TABLE_USERS)
data class User(
    @PrimaryKey(autoGenerate = true)
    val idUser: Int,
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)