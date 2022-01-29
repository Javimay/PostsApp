package com.javimay.postsapp.domain.repository

import com.javimay.postsapp.data.model.User

interface UsersRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(userId: Int): User
    suspend fun updateUsers(): List<User>
}