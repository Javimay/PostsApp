package com.javimay.postsapp.data.repository.comment.datasource

import com.javimay.postsapp.data.model.Comment

interface CommentCacheDataSource {
    suspend fun getCommentsFromCache(): List<Comment>
    suspend fun saveCommentToCache(comments: List<Comment>)
}