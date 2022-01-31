package com.javimay.postsapp.ui.content

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.javimay.postsapp.data.model.Post
import com.javimay.postsapp.domain.usescases.comments.GetCommentByPostUseCase
import com.javimay.postsapp.domain.usescases.post.UpdatePostUseCase
import com.javimay.postsapp.domain.usescases.user.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentPostViewModel @Inject constructor(
    private val userByIdUseCase: GetUserByIdUseCase,
    private val commentByPostUseCase: GetCommentByPostUseCase,
    private val updatePostUseCase: UpdatePostUseCase
):ViewModel(), LifecycleObserver {

    fun getUserById(userId: Int) = liveData{
        emit(userByIdUseCase.execute(userId))
    }

    fun getCommentsByPost(postId: Int) = liveData{
        val comments = commentByPostUseCase.execute(postId)
        emit(comments)
    }

    fun updatePost(post: Post)=CoroutineScope(Dispatchers.IO).launch {
        updatePostUseCase.execute(post)
    }
}
