package com.javimay.postsapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.javimay.postsapp.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsAdapter @Inject constructor(
    private val usersList: MutableList<User>,
    private val fragment: Fragment
): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }

}