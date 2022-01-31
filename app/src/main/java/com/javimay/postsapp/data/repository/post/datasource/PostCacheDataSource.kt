package com.javimay.postsapp.data.repository.post.datasource

import com.javimay.postsapp.data.model.Post

interface PostCacheDataSource {
    suspend fun getPostsFromCache(): List<Post>
    suspend fun savePostsToCache(posts: List<Post>)
}