package com.javimay.postsapp.data.repository.post.datasourceimpl

import com.javimay.postsapp.data.api.PostService
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.repository.post.datasource.PostRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService
): PostRemoteDataSource {
    override suspend fun getPosts(): Response<List<Post>> = postService.getPosts()

}