package com.javimay.postsapp.data.repository.user.datasourceimpl

import com.javimay.postsapp.data.api.PostService
import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.data.repository.user.datasource.UserRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val postService: PostService
): UserRemoteDataSource {
    override suspend fun getUsers(): Response<List<User>> = postService.getUsers()
}