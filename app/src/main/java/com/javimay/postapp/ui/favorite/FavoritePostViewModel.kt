package com.javimay.postsapp.ui.favorite

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.javimay.postsapp.domain.usescases.post.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritePostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
):ViewModel(), LifecycleObserver {
    fun downloadFavoritePost() = liveData {
        val posts = getPostsUseCase.execute()
        emit(posts)
    }

}