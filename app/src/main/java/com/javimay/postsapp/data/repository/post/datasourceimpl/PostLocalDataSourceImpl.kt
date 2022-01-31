package com.javimay.postsapp.data.repository.post.datasourceimpl

import com.javimay.postsapp.data.db.PostDao
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.repository.post.datasource.PostLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostLocalDataSourceImpl @Inject constructor(
    private val postDao: PostDao
): PostLocalDataSource {
    companion object{
        val TAG = PostLocalDataSourceImpl::class.simpleName
    }

    override suspend fun getPostsFromDb(): List<Post> = postDao.getPosts()

    override suspend fun savePostsToDb(posts: List<Post>) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.savePosts(posts)
        }
    }

    override suspend fun updatePost(post: Post) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.updatePosts(post)
        }
    }

    override suspend fun deletePostById(postId: Int): Int {
        var postDeleted = 0
        CoroutineScope(Dispatchers.IO).launch {
            postDeleted = postDao.deletePostById(postId)
        }
        return postDeleted
    }

    override suspend fun clearAll(): Int {
       var rowsDeleted = 0
        CoroutineScope(Dispatchers.IO).launch {
          rowsDeleted = postDao.deleteAllPosts()
        }
        return rowsDeleted
    }
}