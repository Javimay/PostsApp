package com.javimay.postsapp.data.repository.comment.datasourceImpl

import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.data.repository.comment.datasource.CommentCacheDataSource
import javax.inject.Inject

class CommentCacheDataSourceImpl @Inject constructor():CommentCacheDataSource {
    private var commentList = ArrayList<Comment>()

    override suspend fun getCommentsFromCache(): List<Comment> = commentList

    override suspend fun saveCommentToCache(comments: List<Comment>) {
        commentList.clear()
        commentList = ArrayList(comments)
    }
}