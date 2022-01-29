package com.javimay.postsapp.data.repository.user.datasource

import com.javimay.postsapp.data.model.User

interface UserLocalDataSource {
    suspend fun getUsersFromDb(): List<User>
    suspend fun getUserByIdFromDb(userId: Int): User
    suspend fun saveUsersToDb(users: List<User>)
    suspend fun clearAll()
}