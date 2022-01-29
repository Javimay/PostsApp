package com.javimay.postsapp.data.repository.comment.datasourceImpl

import com.javimay.postsapp.data.api.PostService
import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.data.repository.comment.datasource.CommentRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class CommentRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService
):CommentRemoteDataSource {
    override suspend fun getComments(): Response<List<Comment>> = postService.getComments()
}