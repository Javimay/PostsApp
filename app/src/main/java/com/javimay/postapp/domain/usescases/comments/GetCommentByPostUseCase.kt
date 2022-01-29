package com.javimay.postsapp.domain.usescases.comments

import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.domain.repository.CommentsRepository
import javax.inject.Inject

class GetCommentByPostUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
) {
    suspend fun execute(postId: Int): List<Comment> = commentsRepository.getCommentByPost(postId)
}