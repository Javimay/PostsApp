package com.javimay.postsapp.data.repository.user

import android.util.Log
import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.data.repository.user.datasource.UserCacheDataSource
import com.javimay.postsapp.data.repository.user.datasource.UserLocalDataSource
import com.javimay.postsapp.data.repository.user.datasource.UserRemoteDataSource
import com.javimay.postsapp.domain.repository.UsersRepository
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val userCacheDataSource: UserCacheDataSource
): UsersRepository {
    companion object{
        val TAG = UsersRepositoryImpl::class.simpleName
    }

    override suspend fun getUsers(): List<User> = getUsersFromCache()

    override suspend fun getUserById(userId: Int): User {
        return userLocalDataSource.getUserByIdFromDb(userId)
    }

    override suspend fun updateUsers(): List<User> {
        val newUsers: List<User> = getUsersFromApi()
        userLocalDataSource.clearAll()
        userLocalDataSource.saveUsersToDb(newUsers)
        userCacheDataSource.saveUsersToCache(newUsers)
        return newUsers
    }

    private suspend fun getUsersFromCache(): List<User> {
        lateinit var usersList: List<User>
        try {
            usersList = userCacheDataSource.getUsersFromCache()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (usersList.isEmpty()){
            usersList = getUsersFromDb()
            userCacheDataSource.saveUsersToCache(usersList)
        }
        return usersList
    }

    private suspend fun getUsersFromDb(): List<User> {
        lateinit var usersList: List<User>
        try {
            usersList = userLocalDataSource.getUsersFromDb()
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        if (usersList.isNullOrEmpty()){
            usersList = getUsersFromApi()
            userLocalDataSource.saveUsersToDb(usersList)
        }
        return usersList
    }

    private suspend fun getUsersFromApi(): List<User> {
        lateinit var usersList: List<User>
        try {
            val response: Response<List<User>> = userRemoteDataSource.getUsers()
            val body = response.body()
            if (body != null){
                usersList = body
            }
        }catch (exception: Exception){
            Log.i(TAG, exception.message.toString())
        }
        return usersList
    }
}