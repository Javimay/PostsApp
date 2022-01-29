package com.javimay.postsapp.data.repository.user.datasource

import com.javimay.postsapp.data.model.User


interface UserCacheDataSource {
    suspend fun getUsersFromCache(): List<User>
    suspend fun saveUsersToCache(users: List<User>)
}