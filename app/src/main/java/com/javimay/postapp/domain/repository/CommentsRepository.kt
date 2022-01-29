package com.javimay.postsapp.domain.repository

import com.javimay.postsapp.data.model.Comment

interface CommentsRepository {
    suspend fun getComments(): List<Comment>
    suspend fun getCommentByPost(postId: Int): List<Comment>
}