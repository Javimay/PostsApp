package com.javimay.postsapp.data.repository.post.datasource

import com.javimay.postsapp.data.model.Post
import retrofit2.Response

interface PostRemoteDataSource {
    suspend fun getPosts(): Response<List<Post>>
}