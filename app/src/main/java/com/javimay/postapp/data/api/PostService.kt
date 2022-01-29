package com.javimay.postsapp.data.api

import com.javimay.postsapp.data.model.Comment
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.utils.GET_COMMENTS
import com.javimay.postsapp.utils.GET_POSTS
import com.javimay.postsapp.utils.GET_USERS
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET(GET_USERS)
    suspend fun getUsers(): Response<List<User>>

    @GET(GET_POSTS)
    suspend fun getPosts(): Response<List<Post>>

    @GET(GET_COMMENTS)
    suspend fun getComments(): Response<List<Comment>>

}