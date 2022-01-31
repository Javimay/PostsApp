package com.javimay.postsapp.di

import com.javimay.postsapp.data.repository.comment.datasource.CommentCacheDataSource
import com.javimay.postsapp.data.repository.comment.datasourceImpl.CommentCacheDataSourceImpl
import com.javimay.postsapp.data.repository.post.datasource.PostCacheDataSource
import com.javimay.postsapp.data.repository.post.datasourceimpl.PostCacheDataSourceImpl
import com.javimay.postsapp.data.repository.user.datasource.UserCacheDataSource
import com.javimay.postsapp.data.repository.user.datasourceimpl.UserCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
 object CacheDataModule {

    @Provides
    fun providePostCacheDataSource(): PostCacheDataSource{
            return PostCacheDataSourceImpl()
    }

    @Provides
    fun provideUserCacheDataSource(): UserCacheDataSource{
            return UserCacheDataSourceImpl()
    }

    @Provides
    fun provideCommentsCacheDataSource(): CommentCacheDataSource{
        return CommentCacheDataSourceImpl()
    }
}
