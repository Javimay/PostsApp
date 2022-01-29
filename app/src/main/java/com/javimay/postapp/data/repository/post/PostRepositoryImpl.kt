package com.javimay.postsapp.data.repository.post

import android.util.Log
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.repository.post.datasource.PostCacheDataSource
import com.javimay.postsapp.data.repository.post.datasource.PostLocalDataSource
import com.javimay.postsapp.data.repository.post.datasource.PostRemoteDataSource
import com.javimay.postsapp.domain.repository.PostRepository
import kotlinx.coroutines.delay
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postCacheDataSource: PostCacheDataSource,
    private val postLocalDataSource: PostLocalDataSource,
    private val postRemoteDataSource: PostRemoteDataSource
): PostRepository {

    companion object{
        val TAG = PostRepositoryImpl::class.simpleName
    }
    override suspend fun getPosts(): List<Post> = getPostsFromCache()

    override suspend fun updatePost(post: Post){
        try {
            postLocalDataSource.updatePost(post)
            delay(5000)
            val postList = getPostsFromCache().toMutableList()
            postList[post.id-1] = post
            postCacheDataSource.savePostsToCache(postList.toList())
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    override suspend fun deleteAllPosts() {
            try {
               val rowsDeleted = postLocalDataSource.clearAll()
                Log.i(TAG, "Rows deleted: $rowsDeleted")
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
    }

    private suspend fun getPostsFromCache(): List<Post> {
        lateinit var postsList: List<Post>
        try {
            postsList = postCacheDataSource.getPostsFromCache()
        }catch (exception: Exception){
            Log.e(TAG, exception.message.toString())
        }
        if (postsList.isNullOrEmpty()){
            postsList = getPostsFromDB()
            postCacheDataSource.savePostsToCache(postsList)
        }
        return postsList
    }

    private suspend fun getPostsFromDB(): List<Post> {
        lateinit var postsList: List<Post>
        try {
            postsList = postLocalDataSource.getPostsFromDb()
        }catch (exception: Exception){
            Log.e(TAG, exception.message.toString())
        }
        if (postsList.isNullOrEmpty()){
            postsList = getPostsFromAPI()
            postLocalDataSource.savePostsToDb(postsList)
        }
        return postsList
    }

    private suspend fun getPostsFromAPI(): List<Post> {
        lateinit var postsList: List<Post>
        try {
            val response: Response<List<Post>> = postRemoteDataSource.getPosts()
            val body = response.body()
            if (body != null){
                postsList = body
                postsList.forEach{
                    if (it.id<=20){
                        it.read=true
                    }
                }
            }
        }catch (exception: Exception){
            Log.e(TAG, exception.message.toString())
        }
        return postsList
    }
}