package com.javimay.postsapp.data.repository.post.datasource

import com.javimay.postsapp.data.model.Post

interface PostLocalDataSource {
    suspend fun getPostsFromDb(): List<Post>
    suspend fun savePostsToDb(posts: List<Post>)
    suspend fun updatePost(post: Post)
    suspend fun deletePostById(postId: Int): Int
    suspend fun clearAll(): Int
}