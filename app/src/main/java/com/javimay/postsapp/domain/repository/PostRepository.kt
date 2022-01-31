package com.javimay.postsapp.domain.repository

import com.javimay.postsapp.data.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun updatePost(post: Post)
    suspend fun deleteAllPosts()
    suspend fun deletePostById(postId: Int)
}