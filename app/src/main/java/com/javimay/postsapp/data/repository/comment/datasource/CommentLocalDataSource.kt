package com.javimay.postsapp.data.repository.comment.datasource

import com.javimay.postsapp.data.model.Comment

interface CommentLocalDataSource {
    suspend fun getCommentsFromDb(): List<Comment>
    suspend fun getCommentsByPostFromDb(postId: Int): List<Comment>
    suspend fun saveCommentsToDb(comments: List<Comment>)
    suspend fun clearAll(): Int
}