package com.javimay.postsapp.data.repository.comment.datasource

import com.javimay.postsapp.data.model.Comment
import retrofit2.Response

interface CommentRemoteDataSource {
    suspend fun getComments(): Response<List<Comment>>
}