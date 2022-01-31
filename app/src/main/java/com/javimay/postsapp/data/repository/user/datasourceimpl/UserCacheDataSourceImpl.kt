package com.javimay.postsapp.data.repository.user.datasourceimpl

import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.data.repository.user.datasource.UserCacheDataSource
import javax.inject.Inject

class UserCacheDataSourceImpl @Inject constructor(): UserCacheDataSource {

   private var userList= ArrayList<User>()
    override suspend fun getUsersFromCache(): List<User> = userList

    override suspend fun saveUsersToCache(users: List<User>) {
        userList.clear()
        userList = ArrayList(users)
    }
}