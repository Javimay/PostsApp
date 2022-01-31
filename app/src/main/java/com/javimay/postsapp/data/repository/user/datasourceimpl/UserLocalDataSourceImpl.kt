package com.javimay.postsapp.data.repository.user.datasourceimpl

import com.javimay.postsapp.data.db.UserDao
import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.data.repository.user.datasource.UserLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val userDao: UserDao):
    UserLocalDataSource {
    override suspend fun getUsersFromDb(): List<User> = userDao.getUsers()

    override suspend fun getUserByIdFromDb(userId: Int): User {
        return userDao.getUserById(userId)
    }

    override suspend fun saveUsersToDb(users: List<User>) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.saveUser(users)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAllUsers()
        }
    }
}