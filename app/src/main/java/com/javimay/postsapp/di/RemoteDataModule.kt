package com.javimay.postsapp.di

import com.javimay.postsapp.data.api.PostService
import com.javimay.postsapp.data.repository.comment.datasource.CommentRemoteDataSource
import com.javimay.postsapp.data.repository.comment.datasourceImpl.CommentRemoteDataSourceImpl
import com.javimay.postsapp.data.repository.post.datasource.PostRemoteDataSource
import com.javimay.postsapp.data.repository.post.datasourceimpl.PostRemoteDataSourceImpl
import com.javimay.postsapp.data.repository.user.datasource.UserRemoteDataSource
import com.javimay.postsapp.data.repository.user.datasourceimpl.UserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Singleton
    @Provides
    fun providePostRemoteDataSource(postService: PostService): PostRemoteDataSource
            = PostRemoteDataSourceImpl(postService)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(postService: PostService): UserRemoteDataSource
            = UserRemoteDataSourceImpl(postService)
    @Singleton
    @Provides
    fun provideCommentRemoteDataSource(postService: PostService): CommentRemoteDataSource
            = CommentRemoteDataSourceImpl(postService)
}