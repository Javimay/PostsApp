package com.javimay.postsapp.data.repository.user.datasource

import com.javimay.postsapp.data.model.User
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun getUsers(): Response<List<User>>
}