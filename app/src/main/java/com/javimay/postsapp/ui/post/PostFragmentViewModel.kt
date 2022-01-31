package com.javimay.postsapp.ui.post

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.domain.usescases.comments.GetCommentsUseCase
import com.javimay.postsapp.domain.usescases.post.DeletePostsUseCase
import com.javimay.postsapp.domain.usescases.post.GetPostsUseCase
import com.javimay.postsapp.domain.usescases.user.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class PostFragmentViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostsUseCase: DeletePostsUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
):ViewModel(), LifecycleObserver {

    val loading = MutableLiveData<Boolean>()
    private var getUsersJob: Job? = null
    private var getPostsJob: Job? = null


    fun getUsers() = liveData {
        loading.postValue(true)
        var users: List<User>? = null
        getUsersJob = CoroutineScope(Dispatchers.IO).launch {
            users = getUsersUseCase.execute()
        }
        emit(users)

    }

    fun getPosts() = liveData{
        val posts = getPostsUseCase.execute()
//        loading.postValue(false)
        emit(posts)
    }

    fun updatePosts() = liveData{
        val posts = getPostsUseCase.execute()
//        loading.postValue(false)
        emit(posts)
    }

    fun getComments() = CoroutineScope(Dispatchers.IO).launch{
        getCommentsUseCase.execute()
        loading.postValue(false)
    }

    fun cancelJobs(){
        if (getUsersJob!= null && getPostsJob != null) {
            getUsersJob!!.cancel()
            getPostsJob!!.cancel()
        }
    }

    fun deleteAllPosts(){
        CoroutineScope(Dispatchers.IO).launch {
            deletePostsUseCase.execute()
            delay(5000)
        }
    }
}