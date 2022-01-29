package com.javimay.postsapp.di

import com.javimay.postsapp.data.repository.comment.CommentRepositoryImpl
import com.javimay.postsapp.data.repository.post.PostRepositoryImpl
import com.javimay.postsapp.data.repository.user.UsersRepositoryImpl
import com.javimay.postsapp.domain.repository.CommentsRepository
import com.javimay.postsapp.domain.repository.PostRepository
import com.javimay.postsapp.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun providePostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    @Binds
    fun provideUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepository

    @Binds
    fun provideCommentRepository(
        commentRepositoryImpl: CommentRepositoryImpl
    ): CommentsRepository
}
