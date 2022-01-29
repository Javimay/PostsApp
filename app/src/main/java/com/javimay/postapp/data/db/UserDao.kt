package com.javimay.postsapp.data.db

import androidx.room.*
import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.utils.TABLE_USERS

@Dao
interface UserDao {

    @Query("SELECT * FROM $TABLE_USERS")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM $TABLE_USERS WHERE id LIKE :userId")
    suspend fun getUserById(userId: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(users: List<User>)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM $TABLE_USERS")
    suspend fun deleteAllUsers()
}