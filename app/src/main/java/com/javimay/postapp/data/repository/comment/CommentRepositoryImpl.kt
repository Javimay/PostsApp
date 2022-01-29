package com.javimay.postsapp.data.repository.comment

import android.util.Log
import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.data.repository.comment.datasource.CommentCacheDataSource
import com.javimay.postsapp.data.repository.comment.datasource.CommentLocalDataSource
import com.javimay.postsapp.data.repository.comment.datasource.CommentRemoteDataSource
import com.javimay.postsapp.data.repository.post.PostRepositoryImpl
import com.javimay.postsapp.domain.repository.CommentsRepository
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentCacheDataSource: CommentCacheDataSource,
    private val commentLocalDataSource: CommentLocalDataSource,
    private val commentRemoteDataSource: CommentRemoteDataSource
): CommentsRepository {
    companion object{
        val TAG = CommentRepositoryImpl::class.simpleName
    }

    override suspend fun getComments(): List<Comment> = getCommentsFromCache()

    override suspend fun getCommentByPost(postId: Int): List<Comment> {
        return commentLocalDataSource.getCommentsByPostFromDb(postId)
    }

    private suspend fun getCommentsFromCache(): List<Comment> {
        lateinit var commentList: List<Comment>
        try {
            commentList = commentCacheDataSource.getCommentsFromCache()
        }catch (exception: Exception){
            Log.e(PostRepositoryImpl.TAG, exception.message.toString())
        }
        if (commentList.isNullOrEmpty()){
            commentList = getCommentsFromDB()
            commentCacheDataSource.saveCommentToCache(commentList)
        }
        return commentList
    }

    private suspend fun getCommentsFromDB(): List<Comment> {
        lateinit var commentList: List<Comment>
        try {
            commentList = commentLocalDataSource.getCommentsFromDb()
        }catch (exception: Exception){
            Log.e(PostRepositoryImpl.TAG, exception.message.toString())
        }
        if (commentList.isNullOrEmpty()){
            commentList = getCommentsFromAPI()
            commentLocalDataSource.saveCommentsToDb(commentList)
        }
        return commentList
    }

    private suspend fun getCommentsFromAPI(): List<Comment> {
        lateinit var commentList: List<Comment>
        try {
            val response: Response<List<Comment>> = commentRemoteDataSource.getComments()
            val body = response.body()
            if (body != null){
                commentList = body
            }
        }catch (exception: Exception){
            Log.e(PostRepositoryImpl.TAG, exception.message.toString())
        }
        return commentList
    }
}