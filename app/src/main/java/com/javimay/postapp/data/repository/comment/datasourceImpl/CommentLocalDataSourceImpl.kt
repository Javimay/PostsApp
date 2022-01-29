package com.javimay.postsapp.data.repository.comment.datasourceImpl

import com.javimay.postsapp.data.db.CommentDao
import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.data.repository.comment.datasource.CommentLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentLocalDataSourceImpl @Inject constructor(
    private val commentDao: CommentDao
):CommentLocalDataSource {
    override suspend fun getCommentsFromDb(): List<Comment> = commentDao.getComments()

    override suspend fun getCommentsByPostFromDb(postId: Int): List<Comment> =commentDao.getCommentByPost(postId)

    override suspend fun saveCommentsToDb(comments: List<Comment>) {
        CoroutineScope(Dispatchers.IO).launch {
            commentDao.saveComments(comments)
        }
    }

    override suspend fun clearAll(): Int {
        var rowsDeleted = 0
        CoroutineScope(Dispatchers.IO).launch {
            rowsDeleted = commentDao.deleteAllComments()
        }
        return rowsDeleted
    }
}