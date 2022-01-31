package com.javimay.postsapp.domain.usescases.post

import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
){
    suspend fun execute(): List<Post> = postRepository.getPosts()
}