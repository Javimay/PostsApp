package com.javimay.postsapp.data.db

import androidx.room.*
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.utils.TABLE_POSTS

@Dao
interface PostDao {

    @Query("SELECT * FROM $TABLE_POSTS")
    suspend fun getPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePosts(posts: List<Post>)

    @Query("UPDATE $TABLE_POSTS SET favorite = ${1} WHERE id LIKE :idPost")
    suspend fun savePostAsFavorite(idPost: Int)

    @Update
    suspend fun updatePosts(post: Post)

    @Query("DELETE FROM $TABLE_POSTS")
    suspend fun deleteAllPosts(): Int
}