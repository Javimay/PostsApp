package com.javimay.postsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javimay.postsapp.utils.TABLE_COMMENTS

@Entity(tableName = TABLE_COMMENTS)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val isComment: Int,
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)