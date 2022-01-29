package com.javimay.postsapp.data.repository.post.datasourceimpl

import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.repository.post.datasource.PostCacheDataSource
import javax.inject.Inject

class PostCacheDataSourceImpl @Inject constructor(): PostCacheDataSource {

    private var postsList = ArrayList<Post>()

    override suspend fun getPostsFromCache(): List<Post> = postsList

    override suspend fun savePostsToCache(posts: List<Post>) {
        postsList.clear()
        postsList = ArrayList(posts)
    }
}