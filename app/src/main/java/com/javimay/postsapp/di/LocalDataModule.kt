package com.javimay.postsapp.di

import com.javimay.postsapp.data.db.CommentDao
import com.javimay.postsapp.data.db.PostDao
import com.javimay.postsapp.data.db.UserDao
import com.javimay.postsapp.data.repository.comment.datasource.CommentLocalDataSource
import com.javimay.postsapp.data.repository.comment.datasourceImpl.CommentLocalDataSourceImpl
import com.javimay.postsapp.data.repository.post.datasource.PostLocalDataSource
import com.javimay.postsapp.data.repository.post.datasourceimpl.PostLocalDataSourceImpl
import com.javimay.postsapp.data.repository.user.datasource.UserLocalDataSource
import com.javimay.postsapp.data.repository.user.datasourceimpl.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Singleton
    @Provides
    fun providePostLocalDataSource(postDao: PostDao): PostLocalDataSource
            = PostLocalDataSourceImpl(postDao)

    @Singleton
    @Provides
    fun provideUserLocalDataSource(userDao: UserDao): UserLocalDataSource
            = UserLocalDataSourceImpl(userDao)

    @Singleton
    @Provides
    fun provideCommentLocalDataSource(commentDao: CommentDao): CommentLocalDataSource
            = CommentLocalDataSourceImpl(commentDao)
}