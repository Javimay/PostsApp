package com.javimay.postsapp.data.db

import androidx.room.*
import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.utils.TABLE_COMMENTS

@Dao
interface CommentDao {
    @Query("SELECT * FROM $TABLE_COMMENTS")
    suspend fun getComments(): List<Comment>

    @Query("SELECT * FROM $TABLE_COMMENTS WHERE postId LIKE :idPost")
    suspend fun getCommentByPost(idPost: Int): List<Comment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComments(comments: List<Comment>)

    @Update
    suspend fun updateComments(comment: Comment)

    @Query("DELETE FROM $TABLE_COMMENTS")
    suspend fun deleteAllComments(): Int
}