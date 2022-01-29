package com.javimay.postsapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.javimay.postsapp.ui.favorite.FavoritePostFragment
import com.javimay.postsapp.ui.post.PostFragment
import javax.inject.Inject

class ViewPagerFragmentAdapter @Inject constructor(
    fragment: Fragment,
    private val titles: Array<String>
    ) :
        FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return PostFragment()
                1 -> return FavoritePostFragment()
            }
            return PostFragment()
        }

        override fun getItemCount(): Int {
            return titles.size
        }
}