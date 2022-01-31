package com.javimay.postsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javimay.postsapp.utils.TABLE_POSTS

@Entity(tableName = TABLE_POSTS)
data class Post(
    @PrimaryKey(autoGenerate = true)
    val idPost: Int,
    val body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    var favorite: Boolean,
    var read: Boolean
)