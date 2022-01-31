package com.javimay.postsapp.di

import android.content.Context
import androidx.room.Room
import com.javimay.postsapp.data.db.CommentDao
import com.javimay.postsapp.data.db.PostDao
import com.javimay.postsapp.data.db.PostDataBase
import com.javimay.postsapp.data.db.UserDao
import com.javimay.postsapp.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun providePostDataBase(@ApplicationContext context: Context): PostDataBase = Room.databaseBuilder(
        context, PostDataBase::class.java, DATABASE_NAME
    ).build()

    //Provides DAO's
    @Singleton
    @Provides
    fun providePostDao(postDataBase: PostDataBase): PostDao = postDataBase.postDao()

    @Singleton
    @Provides
    fun provideUserDao(postDataBase: PostDataBase): UserDao = postDataBase.userDao()

    @Singleton
    @Provides
    fun provideCommentDao(postDataBase: PostDataBase): CommentDao = postDataBase.commentDao()
}