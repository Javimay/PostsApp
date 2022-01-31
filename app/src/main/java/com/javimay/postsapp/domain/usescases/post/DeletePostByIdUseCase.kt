package com.javimay.postsapp.domain.usescases.post

import com.javimay.postsapp.domain.repository.PostRepository
import javax.inject.Inject

class DeletePostByIdUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend fun execute(postId: Int) = postRepository.deletePostById(postId)
}